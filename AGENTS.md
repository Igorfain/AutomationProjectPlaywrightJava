````markdown
# Codex Instructions — Java Playwright + TestNG + Allure

## ⚠️ CRITICAL — READ BEFORE WRITING ANY CODE ⚠️

BEFORE writing any code, you MUST:

1. Read ALL rules in this file
2. Validate your code against EVERY rule
3. If you used a lambda → REWRITE it using a for-each loop
4. If you used Stream API → REWRITE it using a for-each loop
5. Verify that you did not invent any methods, classes, fields, locators, or framework APIs

VIOLATIONS ARE NOT ACCEPTABLE.

Any code that violates these rules MUST be rewritten immediately.

⚠️ ALL CONVENTIONS IN THIS FILE ARE MANDATORY AND MUST BE FOLLOWED WITHOUT EXCEPTION.

---

# Project Guidelines

You are an expert Java test automation engineer working with TestNG, Playwright, and Allure reporting.

## PROJECT CONTEXT

- **Framework:** TestNG + Playwright (Microsoft)
- **Language:** Java 17+
- **Architecture:** Page Object Model (POM) + Steps Pattern
- **Reporting:** Allure TestNG
- **Actions wrapper:** ActionBot

---

# STRICT CONVENTIONS (MANDATORY)

## 1. PRIVATE FIELDS

- All private fields MUST NOT use underscore prefixes (Java style, not C#)
- Use `private final` for fields initialized in the constructor
- Declare locators as `private final Locator elementName`
- `ActionBot` is MANDATORY for all Playwright interactions inside POM classes

```java
// ✅ Correct
private final Locator contactUsButton;
private final Locator nameInput;
private final ActionBot actionBot;
private final Page page;

// ❌ Incorrect
private Locator _contactUsButton;
private Locator btn;
```

---

## 2. NO LAMBDAS OR STREAM API

### FORBIDDEN

- `.stream()`
- `.filter()`
- `.map()`
- `.collect()`
- `.forEach()` with lambdas
- Method references (`::`)

### REQUIRED

Use explicit for-each loops for all iterations.

```java
// ✅ Correct
List<String> navigationBarItemTexts = new ArrayList<>();

for (Locator navigationBarElement : navigationBarElements) {
    navigationBarItemTexts.add(
            navigationBarElement.textContent().trim()
    );
}

return navigationBarItemTexts;

// ❌ Forbidden
return navigationBarElements.stream()
        .map(e -> e.textContent().trim())
        .collect(Collectors.toList());
```

---

## 3. DO NOT INVENT FRAMEWORK CODE

### NEVER INVENT

- Methods
- Classes
- Fields
- Utilities
- Helper methods
- Framework APIs
- Locators
- Data providers

### REQUIRED

If the implementation is not provided in the current context:

- Ask for the existing code
- Use only constructs explicitly shown in the project
- Do not assume custom framework functionality exists

```text
❌ Forbidden

landingPage.waitUntilReady();
actionBot.smartClick();
basePage.retryAction();

Unless these methods were explicitly provided.
```

---

## 4. PLAYWRIGHT WAITING RULES

### FORBIDDEN

```java
Thread.sleep(3000);

page.waitForTimeout(5000);
```

### AVOID

```java
page.waitForLoadState();
```

unless there is a demonstrated synchronization issue.

### REQUIRED

Prefer:

- Playwright auto-waits
- Locator-based waits
- Assertions that naturally wait

```java
assertThat(submitButton).isVisible();
```

---

## 5. LOCATOR STRATEGY

### PREFERRED

```java
[data-qa='submit']
[data-testid='submit']
[data-test='submit']
```

### AVOID

```java
xpath=...
```

```java
div:nth-child(4)
```

```java
text=Submit
```

unless no stable alternative exists.

---

## 6. PAGE OBJECT MODEL (POM)

### Location

`infra/pages/`

### Rules

- Class naming: `DescriptivePageName`
- Extend `BasePage`
- Constructor: `public ClassName(Page page)`
- Must contain page locators
- Must contain `ActionBot`
- Methods follow verb-noun naming

Examples:

- `getNavigationItems`
- `fillEmailField`
- `clickLoginButton`

### IMPORTANT

NO ASSERTIONS inside POM.

POM is responsible ONLY for:

- Interactions
- Data retrieval

### Return `this`

Use fluent chaining inside the same page.

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

    public String getSuccessMessageText() {
        return submitButton.textContent();
    }
}
```

---

## 7. STEPS CLASSES

### Location

`steps/`

### Rules

- Naming: `[PageName]Steps`
- Constructor accepts `Page`
- Instantiate POM internally
- Return `this`
- Fluent chaining required

### REQUIRED

Every public method MUST contain:

```java
@Step("Description")
```

and

```java
ConsoleReporter.log("Description");
```

exactly once.

### Assertions belong HERE

```java
@Step("Verify success message")
public ContactUsSteps verifySuccessMessage() {

    ConsoleReporter.log("Verify success message");

    String actualSuccessMessage =
            contactUsPage.getSuccessMessageText();

    Assert.assertEquals(
            actualSuccessMessage,
            "Success",
            "Success message mismatch. Expected: Success, Actual: "
                    + actualSuccessMessage
    );

    return this;
}
```

---

## 8. TEST DATA HELPERS

### Location

`utils/` or `data/`

### Rules

- Prefer simple immutable scenario-specific data classes for grouped test data
- Class should be `final`
- Constructor should be `private`
- Public static factory methods are preferred for valid/invalid datasets
- `public final` fields are allowed when they improve readability in automation tests
- `private final` fields are also allowed when encapsulation is useful for the scenario
- Do not use mutable public fields
- Do not use setters
- Prefer separate classes per scenario instead of one shared `TestData`
- Use final utility classes with public static methods only for simple single values or constants

```java
public final class ContactUsData {

    public final String name;
    public final String email;

    private ContactUsData(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public static ContactUsData validContactUsData() {
        return new ContactUsData("John Doe", "john.doe@mail.com");
    }
}
```

```java
public final class ContactUsFormData {

    private ContactUsFormData() {
    }

    public static String getValidName() {
        return "John Doe";
    }
}
```

---

## 9. TEST CLASSES

### Location

`tests/`

### Rules

- Naming: `[Scenario]Test`
- Extend `BaseTest`
- No business logic
- Only Step method calls

### REQUIRED

```java
@Test(description = "...")
@Story("...")
```

### Login override

```java
@Override
protected boolean doDefaultLogin() {
    return false;
}
```

### BeforeMethod

Only initialize Steps.

```java
@BeforeMethod
public void setUpTest() {
    contactUsSteps = new ContactUsSteps(page);
}
```

---

## 10. COLLECTIONS RULES

### PREFERRED

```java
List<String>
List<Locator>
```

### DO NOT USE

```java
Set<String>
HashSet<String>
LinkedHashSet<String>
```

unless uniqueness is explicitly required.

### DO NOT USE

```java
Map<String, Object>
```

unless the scenario genuinely requires a map.

Prefer simple, readable collections.

---

## 11. CODE STYLE

| Element | Style | Example |
|----------|----------|----------|
| Class fields | camelCase | `contactUsPage` |
| Local variables | camelCase | `navigationBarItemTexts` |
| Method parameters | camelCase | `filePath` |
| Public classes | PascalCase | `ContactUsPage` |
| Public methods | camelCase | `clickSubmitButton` |
| Constants | UPPER_SNAKE_CASE | `DEFAULT_TIMEOUT` |

---

## 12. VARIABLE NAMING STANDARDS

### Local Variables

```java
// ✅ Correct
List<String> navigationBarItemTexts = new ArrayList<>();
List<String> expectedNavigationBarItems = new ArrayList<>();

// ❌ Incorrect
List<String> items = new ArrayList<>();
List<String> list = new ArrayList<>();
```

---

### Loop Variables

```java
// ✅ Correct
for (Locator navigationBarElement : navigationBarElements) {
}

// ❌ Incorrect
for (Locator e : navigationBarElements) {
}
```

---

### Boolean Variables

```java
// ✅ Correct
boolean navigationBarItemFound = false;
boolean isNavigationBarVisible = true;

// ❌ Incorrect
boolean found = false;
boolean result = false;
```

---

### Loop Counters

```java
// ✅ Acceptable
for (int itemIndex = 0;
     itemIndex < navigationBarItems.size();
     itemIndex++) {
}
```

```java
// ❌ Forbidden
for (int i = 0;
     i < navigationBarItems.size();
     i++) {
}
```

---

## 13. ASSERTIONS

Use explicit assertion messages.

```java
Assert.assertEquals(
        actualNavigationBarItems,
        expectedNavigationBarItems,
        "Navigation bar items mismatch. Expected: "
                + expectedNavigationBarItems
                + ", Actual: "
                + actualNavigationBarItems
);
```

Every assertion message must contain:

- Expected value
- Actual value

---

## 14. BASE TEST RULES

### Available Fields

```java
protected Page page;
```

### Login

```java
protected boolean doDefaultLogin()
```

Override only when login is not required.

### BeforeMethod

Only Step initialization.

### AfterMethod

Must contain:

- Screenshot on failure
- Resource cleanup

---

## 15. FINAL VALIDATION CHECKLIST

Before returning code, verify ALL items below:

- [ ] No lambdas
- [ ] No Stream API
- [ ] No method references (`::`)
- [ ] No invented framework methods
- [ ] No invented utilities
- [ ] No `Thread.sleep()`
- [ ] No `waitForTimeout()`
- [ ] No unnecessary `waitForLoadState()`
- [ ] No assertions inside POM
- [ ] Assertions only inside Steps
- [ ] ActionBot used for interactions
- [ ] Proper locator strategy used
- [ ] Explicit assertion messages
- [ ] Fluent chaining preserved
- [ ] Variable names follow conventions
- [ ] Collections follow conventions
- [ ] Code complies with every rule in this document

IF ANY CHECK FAILS — REWRITE THE CODE BEFORE RETURNING IT.
````
