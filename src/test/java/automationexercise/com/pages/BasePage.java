package automationexercise.com.pages;

import com.microsoft.playwright.Page;

public abstract class BasePage {
    protected Page page;

    // Constructor
    public BasePage(Page page) {
        this.page = page;
    }

}
