package saucedemo.com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CheckoutStepTwoPage extends BasePage {

    private final Locator finishButtonLocator;
    private final Locator itemNameLocator;
    private final Locator itemPriceLocator;
    private final Locator paymentInfoValueLocator;
    private final Locator shippingInfoValueLocator;
    private final Locator subtotalValueLocator;
    private final Locator taxValueLocator;
    private final Locator totalValueLocator;

    public CheckoutStepTwoPage(Page page) {
        super(page);
        this.finishButtonLocator = page.locator("[data-test='finish']");
        this.itemNameLocator = page.locator(".inventory_item_name");
        this.itemPriceLocator = page.locator(".inventory_item_price");
        this.paymentInfoValueLocator = page.locator("[data-test='payment-info-value']");
        this.shippingInfoValueLocator = page.locator("[data-test='shipping-info-value']");
        this.subtotalValueLocator = page.locator("[data-test='subtotal-label']");
        this.taxValueLocator = page.locator("[data-test='tax-label']");
        this.totalValueLocator = page.locator("[data-test='total-label']");
    }

    public void clickFinish() {
        finishButtonLocator.click();
    }

    public String getItemName() {
        return itemNameLocator.first().innerText();
    }

    public double getItemPrice() {
        String priceText = itemPriceLocator.first().innerText().replace("$", "").trim();
        return Double.parseDouble(priceText);
    }

    public String getPaymentInfo() {
        return paymentInfoValueLocator.innerText();
    }

    public String getShippingInfo() {
        return shippingInfoValueLocator.innerText();
    }

    public String getSubtotalLabel() {
        return subtotalValueLocator.innerText();
    }

    public String getTaxLabel() {
        return taxValueLocator.innerText();
    }

    public String getTotalLabel() {
        return totalValueLocator.innerText();
    }
}
