package saucedemo.com.pages;

import com.microsoft.playwright.Page;

public abstract class BasePage {
    protected Page page;

    // Constructor
    public BasePage(Page page) {
        this.page = page;
    }

//    // Wait for an element to be visible
//    public void waitForVisibility(String selector) {
//        page.waitForSelector(selector);
//    }
//
//    // Click an element
//    public void click(String selector) {
//        page.click(selector);
//    }
//
//    // Fill input field
//    public void fill(String selector, String text) {
//        page.fill(selector, text);
//    }
//
//    // Get text content of an element
//    public String getText(String selector) {
//        return page.textContent(selector);
//    }
//
//    // Navigate to a URL
//    public void navigate(String url) {
//        page.navigate(url);
//    }
}
