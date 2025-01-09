package tests.lendbuzz;

import com.microsoft.playwright.*;
import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class lendbuzzTestsTodos {

    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    private Page page;

    @BeforeMethod
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext(); // Initialize context
        page = context.newPage(); // Initialize page
        page.navigate("https://todomvc.com/examples/react/dist/");
    }

    @AfterMethod
    public void tearDown() {
        if (context != null) {
            context.close();
        }
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }

    @Test (description = "Add a new task")
    public void addTodo() {

        page.locator(".new-todo").fill("Buy Milk");
        page.keyboard().press("Enter");
        page.waitForTimeout(500);

        Locator todoItem = page.locator(".todo-list li label");
        String todoItemText = todoItem.textContent();
        assertEquals(todoItemText, "Buy Milk", "Todo item text does not match.");
    }

    @Test(description = "Complete task")
    public void completeTask(){
        page.locator(".new-todo").fill("Buy Milk");
        page.keyboard().press("Enter");
        page.waitForTimeout(500);
        page.locator("input[data-testid='todo-item-toggle']").nth(0).check();
        Locator todoItem = page.locator("li[data-testid='todo-item']");
        assertTrue((Boolean) todoItem.evaluate("element => element.classList.contains('completed')"),
                "Todo item is not marked as completed.");
    }

    @Test(description = "Delete task")
    public void deleteTask(){
        page.locator(".new-todo").fill("Buy Milk");
        page.keyboard().press("Enter");
        page.waitForTimeout(500);

        page.locator("label[data-testid='todo-item-label']").nth(0).hover();

        page.locator("button[data-testid='todo-item-button']").click();
        page.waitForTimeout(500);

        assertTrue(page.locator("text=Buy Milk").isHidden(), "'Buy Milk' is still visible, but it shouldn't be.");

    }

    @Test(description = "Filter tasks by completed")
    public void filterTasksByCompleted(){
        page.locator(".new-todo").fill("Buy Milk");
        page.keyboard().press("Enter");
        page.locator(".new-todo").fill("Buy Chanukia");
        page.keyboard().press("Enter");
        page.waitForTimeout(500);
        page.locator("input[data-testid='todo-item-toggle']").nth(0).check();
        page.locator("a[href='#/completed']").click();

        page.waitForTimeout(500);
        assertTrue(page.locator("text=Buy Chanukia").isHidden(), "'Buy Chanukia' is still visible, but it shouldn't be.");

        Locator todoItem = page.locator("li[data-testid='todo-item']");
        assertTrue((Boolean) todoItem.evaluate("element => element.classList.contains('completed')"),
                "Todo item is not marked as completed.");


    }


}
