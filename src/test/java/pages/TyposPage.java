package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class TyposPage extends BasePage{
    public TyposPage(Page page) {
        super(page);
    }

    public Locator header = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Typos").setExact(true));
    public Locator description = page.locator(".example p").first();
    public Locator typoText = page.locator(".example p").nth(1);
}
