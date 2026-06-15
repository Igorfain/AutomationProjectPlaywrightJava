# CLAUDE Instructions — Java Playwright + TestNG + Allure

## ⚠️ КРИТИЧНО — ПРОЧИТАЙ ПЕРЕД НАПИСАНИЕМ ЛЮБОГО КОДА ⚠️

ПЕРЕД написанием кода ты ОБЯЗАН:
1. Прочитать ВСЕ правила в этом файле
2. Проверить код по КАЖДОМУ правилу
3. Если использовал лямбду → ПЕРЕПИШИ через for-each
4. Если использовал Stream API → ПЕРЕПИШИ через for-each

НАРУШЕНИЯ НЕДОПУСТИМЫ.
Любой код, нарушающий правила, ДОЛЖЕН быть переписан немедленно.
⚠️ ВСЕ КОНВЕНЦИИ В ЭТОМ ФАЙЛЕ ОБЯЗАТЕЛЬНЫ И ДОЛЖНЫ СОБЛЮДАТЬСЯ БЕЗ ИСКЛЮЧЕНИЙ.

---

## Project Guidelines

Ты — эксперт-инженер по автоматизации тестирования на Java, работающий с TestNG, Playwright и Allure reporting.

### PROJECT CONTEXT:
- **Framework:** TestNG + Playwright (Microsoft)
- **Language:** Java 17+
- **Architecture:** Page Object Model (POM) + Steps Pattern
- **Reporting:** Allure TestNG
- **Actions wrapper:** ActionBot

---

## СТРОГИЕ КОНВЕНЦИИ (ОБЯЗАТЕЛЬНЫ):

### 1. ПРИВАТНЫЕ ПОЛЯ (PRIVATE FIELDS):
- Все приватные поля НЕ используют underscore prefix (Java-стиль, не C#)
- Используй `private final` для инициализируемых в конструкторе полей
- Локаторы объявляй как `private final Locator elementName`
- `ActionBot` ОБЯЗАТЕЛЕН для всех Playwright-взаимодействий в POM

```java
// ✅ Правильно
private final Locator contactUsButton;
private final Locator nameInput;
private final ActionBot actionBot;
private final Page page;

// ❌ Неправильно
private Locator _contactUsButton;
private Locator btn;
```

---

### 2. БЕЗ ЛЯМБД И STREAM API:
- **ЗАПРЕЩЕНО:** `.stream()`, `.filter()`, `.map()`, `.collect()`, `.forEach()` с лямбдами
- **ОБЯЗАТЕЛЬНО:** явные циклы for-each для всех итераций

```java
// ✅ Правильно
List<String> navigationBarItemTexts = new ArrayList<>();
for (Locator navigationBarElement : navigationBarElements) {
    navigationBarItemTexts.add(navigationBarElement.textContent().trim());
}
return navigationBarItemTexts;

// ❌ Запрещено
return navigationBarElements.stream()
    .map(e -> e.textContent().trim())
    .collect(Collectors.toList());
```

---

### 3. PAGE OBJECT MODEL (POM) — Расположение: `infra/pages/`
- **Именование класса:** `DescriptivePageName` (напр. `LandingPage`, `LoginPage`, `ContactUsPage`)
- **Базовый класс:** наследовать от `BasePage`
- **Конструктор:** `public ClassName(Page page)`
- **Обязательные поля:** `actionBot`, `page`, локаторы страницы
- **Методы:** паттерн глагол-существительное (`getNavigationItems`, `fillEmailField`, `clickLoginButton`)
- **БЕЗ ASSERTIONS в POM** — только взаимодействия и получение данных
- **Возвращай `this`** для fluent chaining внутри одной страницы

```java
public class ContactUsPage extends BasePage {

    private final Locator nameInput;
    private final Locator submitButton;
    private final ActionBot actionBot;

    public ContactUsPage(Page page) {
        super(page);
        this.actionBot = new ActionBot(page);
        this.nameInput = page.locator("[data-qa='name']");
        this.submitButton = page.locator("input[type='submit']");
    }

    public ContactUsPage insertName(String name) {
        actionBot.type(nameInput, name, 50);
        return this;
    }

    public ContactUsPage clickSubmitButton() {
        actionBot.click(submitButton);
        return this;
    }
}
```

---

### 4. STEPS КЛАССЫ — Расположение: `steps/`
- **Именование класса:** `[PageName]Steps` (напр. `ContactUsSteps`, `LoginSteps`)
- **Конструктор:** принимает `Page`, инстанциирует POM внутри
- **Приватное поле:** `private final [POMClass] [pomInstanceName];`
- **Именование методов:** `verify*`, `perform*`, `execute*`, `click*`, `fill*`
- **ОБЯЗАТЕЛЬНО возвращать `this`** для fluent chaining
- **ОБЯЗАТЕЛЬНО** `ConsoleReporter.log()` перед каждым действием
- **ОБЯЗАТЕЛЬНО** `@Step("описание")` на всех публичных методах
- Assertions с явными сообщениями об ошибках (actual vs expected)

```java
public class ContactUsSteps {

    private final ContactUsPage contactUsPage;

    public ContactUsSteps(Page page) {
        this.contactUsPage = new ContactUsPage(page);
    }

    @Step("Click on 'Contact Us' button")
    public ContactUsSteps clickContactUsButton() {
        ConsoleReporter.log("Click on 'Contact Us' button");
        contactUsPage.clickContactUsButton();
        return this;
    }

    @Step("Verify success message is visible after form submission")
    public ContactUsSteps verifySuccessMessage() {
        ConsoleReporter.log("Verify success message is visible after form submission");
        contactUsPage.verifySuccessMessage();
        return this;
    }
}
```

---

### 5. TEST DATA HELPER — Расположение: `utils/` или `data/`
- **Именование класса:** `[Scenario]Data` или `[PageName]NavigationData`
- **ОБЯЗАТЕЛЬНО:** `final class` с `private` конструктором и `public static` методами
- **Тип возврата:** `List<String>`, `Map<String, String>` и т.п.

```java
public final class ContactUsFormData {

    private ContactUsFormData() {}

    public static String getValidName() {
        return "John Doe";
    }

    public static List<String> getLandingPageNavigationBarItems() {
        List<String> navigationBarItems = new ArrayList<>();
        navigationBarItems.add("Home");
        navigationBarItems.add("Products");
        navigationBarItems.add("Cart");
        return navigationBarItems;
    }
}
```

---

### 6. TEST КЛАССЫ — Расположение: `tests/`
- **Именование класса:** `[Scenario]Test` (напр. `ContactUsTest`, `OrderingTest`)
- **ОБЯЗАТЕЛЬНО** наследовать от `BaseTest`
- **Приватное поле Steps:** `private [StepsClass] [stepsInstanceName];`
- **`@BeforeMethod`:** инициализировать steps с `page`
- **`@Override doDefaultLogin()`:** `return false;` если тест без логина
- **Именование методов:** camelCase, описательное (напр. `submitContactFormWithValidDataTest`)
- **ОБЯЗАТЕЛЬНО:** `@Test(description = "...")` и `@Epic`, `@Story`, `@Tag` из Allure
- **БЕЗ бизнес-логики** — только вызов steps

```java
@Epic("AutomationExercise UI Tests")
@Tag("ContactUs")
public class ContactUsTest extends BaseTest {

    private ContactUsSteps contactUsSteps;

    @Override
    protected boolean doDefaultLogin() {
        return false;
    }

    @BeforeMethod
    public void setUpTest() {
        contactUsSteps = new ContactUsSteps(page);
    }

    @Test(description = "Submit contact form with valid data and verify success")
    @Story("Contact Us Form")
    public void submitContactFormWithValidDataTest() {
        contactUsSteps
                .clickContactUsButton()
                .verifyGetInTouchText()
                .fillRelevantFields(
                        ContactUsFormData.getValidName(),
                        ContactUsFormData.getValidEmail(),
                        ContactUsFormData.getValidSubject(),
                        ContactUsFormData.getValidMessage()
                )
                .clickSubmitButton()
                .verifySuccessMessage();
    }
}
```

---

### 7. CODE STYLE:

| Элемент | Стиль | Пример |
|---|---|---|
| Поля класса | camelCase | `contactUsPage`, `actionBot` |
| Локальные переменные | camelCase | `navigationBarItemTexts` |
| Параметры методов | camelCase | `filePath`, `sortingOption` |
| Классы / методы публичные | PascalCase / camelCase | `ContactUsPage`, `clickSubmitButton` |
| Константы | UPPER_SNAKE_CASE | `DEFAULT_TIMEOUT` |

---

### 8. СТАНДАРТЫ ИМЕНОВАНИЯ ПЕРЕМЕННЫХ:

#### A. ЛОКАЛЬНЫЕ ПЕРЕМЕННЫЕ И КОЛЛЕКЦИИ:
```java
// ✅ Правильно
List<String> navigationBarItemTexts = new ArrayList<>();
List<String> expectedNavigationBarItems = new ArrayList<>();
List<Locator> navigationBarElements = page.locator(".nav-item").all();

// ❌ Неправильно
List<String> items = new ArrayList<>();
List<String> list = new ArrayList<>();
List<Locator> x = page.locator(".nav-item").all();
```

#### B. ПЕРЕМЕННЫЕ ЦИКЛА:
```java
// ✅ Правильно
for (Locator navigationBarElement : navigationBarElements) { ... }
for (String expectedNavigationBarItem : expectedNavigationBarItems) { ... }

// ❌ Неправильно
for (Locator e : elements) { ... }
for (String item : items) { ... }
for (int i = 0; i < items.size(); i++) { ... }
```

#### C. BOOLEAN ПЕРЕМЕННЫЕ:
```java
// ✅ Правильно
boolean navigationBarItemFound = false;
boolean isNavigationBarVisible = true;
boolean hasNavigationBarItems = navigationBarElements.size() > 0;

// ❌ Неправильно
boolean found = false;
boolean f = false;
boolean result = false;
```

#### D. СЧЁТЧИКИ ЦИКЛОВ (если абсолютно необходимо):
```java
// ✅ Правильно — предпочтительно
for (Locator navigationBarElement : navigationBarElements) { ... }

// ✅ Допустимо если нужен индекс
for (int itemIndex = 0; itemIndex < navigationBarItems.size(); itemIndex++) { ... }

// ❌ Запрещено
for (int i = 0; i < items.size(); i++) { ... }
```

#### E. FEATURE-SPECIFIC ИМЕНОВАНИЕ:
```java
// ✅ Правильно
List<String> navigationBarItemTexts = ...
List<Locator> loginFormInputFields = ...
List<String> expectedNavigationBarItems = ...

// ❌ Неправильно
List<String> itemTexts = ...
List<Locator> inputFields = ...
List<String> elements = ...
```

#### F. ПЕРЕМЕННЫЕ ВОЗВРАТА ИЗ МЕТОДОВ:
```java
// ✅ Правильно
List<String> navigationBarItems = landingPage.getNavigationBarItems();
String loginErrorMessage = loginPage.getErrorMessage();
String currentUsername = userPage.getLoggedInUsername();

// ❌ Неправильно
List<String> result = landingPage.getNavigationBarItems();
String data = loginPage.getErrorMessage();
```

#### G. ПЕРЕМЕННЫЕ ТЕСТОВЫХ ДАННЫХ:
```java
// ✅ Правильно
List<String> expectedNavigationBarItems = LandingPageNavigationData.getNavigationBarItems();
List<String> actualNavigationBarItems = landingPage.getNavigationBarItems();
String expectedErrorMessage = "Invalid credentials";

// ❌ Неправильно
List<String> items = LandingPageNavigationData.getNavigationBarItems();
List<String> data = landingPage.getNavigationBarItems();
String expected = "Invalid credentials";
```

---

### 9. ASSERTIONS:

Используй TestNG assertions с явными сообщениями:

```java
// ✅ Правильно
Assert.assertEquals(
    actualNavigationBarItems,
    expectedNavigationBarItems,
    "Navigation bar items mismatch. Expected: " + expectedNavigationBarItems + ", Actual: " + actualNavigationBarItems
);

// Или Playwright assertions в POM
assertThat(successMessage).hasText("Success! Your details have been submitted successfully.");
```

---

### 10. BASE TEST — Ключевые правила:
- `protected Page page;` — доступен всем тестам
- `protected boolean doDefaultLogin()` — override в тесте если не нужен логин
- `@BeforeMethod` в тесте — только инициализация steps
- `@AfterMethod` — скриншот при падении + закрытие ресурсов
