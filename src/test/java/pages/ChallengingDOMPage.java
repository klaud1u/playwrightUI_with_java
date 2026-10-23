package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class ChallengingDOMPage extends BasePage {
    public ChallengingDOMPage(Page page) {
        super(page);
    }
    public Locator header = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Challenging DOM"));
    public Locator blackBtn = page.locator("a.button").nth(0);
    public Locator orangeBtn = page.locator("a.button").nth(1);
    public Locator greenBtn = page.locator("a.button").nth(2);
    public Locator canvas = page.locator("#canvas");}
