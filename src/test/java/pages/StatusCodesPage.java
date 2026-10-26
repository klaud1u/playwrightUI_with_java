package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class StatusCodesPage extends BasePage{
    public  StatusCodesPage(Page page) {
        super(page);
    }

    public Locator header = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Status Codes").setExact(true));
    public Locator statusLinks = page.locator(".example ul li a");
    public Locator message = page.locator(".example p");
    public Locator backLink = page.locator(".example").getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("here").setExact(true));

    public Locator statusCodeLink(int statusCode) {
        return page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(String.valueOf(statusCode)).setExact(true));
    }
}
