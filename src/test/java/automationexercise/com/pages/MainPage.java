package automationexercise.com.pages;

import automationexercise.com.infra.actions.ActionBot;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.ArrayList;
import java.util.List;

public class MainPage extends BasePage {

    private final Locator loggedInText;
    private final Locator navItemLinks;
    private final ActionBot actionBot;
    public final Locator logo;

    public MainPage(Page page) {
        super(page);
        this.actionBot = new ActionBot(page);
        this.loggedInText = page.locator("a i.fa.fa-user + b");
        this.navItemLinks = page.locator("ul.nav.navbar-nav li a");
        this.logo = page.locator("img[alt='Website for automation practice']");
    }

    public String getLoggedInUserText() {
        return actionBot.getText(loggedInText).trim();
    }

    public List<String> getTopBarItemTexts() {
        actionBot.waitForVisibility(navItemLinks.first());
        List<Locator> navItemElements = navItemLinks.all();
        List<String> topBarItemTexts = new ArrayList<>();

        for (Locator navItemElement : navItemElements) {
            topBarItemTexts.add(actionBot.getInnerText(navItemElement).trim());
        }

        return topBarItemTexts;
    }
}
