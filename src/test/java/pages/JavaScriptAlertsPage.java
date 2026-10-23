package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class JavaScriptAlertsPage extends BasePage {
    public JavaScriptAlertsPage(Page page) {
        super(page);
    }

    public Locator header = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("JavaScript Alerts").setExact(true));
    public Locator alertButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Click for JS Alert").setExact(true));
    public Locator confirmButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Click for JS Confirm").setExact(true));
    public Locator promptButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Click for JS Prompt").setExact(true));
    public Locator result = page.locator("#result");
}
