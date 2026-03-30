package saucedemo.com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CheckoutCompletePage extends BasePage {

    private final Locator completeHeaderLocator;
    private final Locator completeTextLocator;
    private final Locator backHomeButtonLocator;
    private final Locator pageTitleLocator;

    public CheckoutCompletePage(Page page) {
        super(page);
        this.completeHeaderLocator = page.locator("[data-test='complete-header']");
        this.completeTextLocator = page.locator("[data-test='complete-text']");
        this.backHomeButtonLocator = page.locator("[data-test='back-to-products']");
        this.pageTitleLocator = page.locator(".title");
    }

    public String getCompleteHeaderText() {
        return completeHeaderLocator.innerText();
    }

    public String getCompleteBodyText() {
        return completeTextLocator.innerText();
    }

    public boolean isBackHomeVisible() {
        return backHomeButtonLocator.isVisible();
    }

    public String getPageTitleText() {
        return pageTitleLocator.innerText();
    }

    public void clickBackHome() {
        backHomeButtonLocator.click();
    }
}
