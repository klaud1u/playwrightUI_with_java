package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class SubdirectoriesPage extends BasePage {
    public SubdirectoriesPage(Page page) {
        super(page);
    }

    public Locator header = page.locator(".example").getByRole(AriaRole.HEADING);
    public Locator currentPath = page.locator(".example > pre");
    public Locator navigationLinks = page.locator(".example ol a");
    public Locator backLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("/subdirectories").setExact(true));

    public Locator subdirectoriesLink(String directory) {
        return page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("/subdirectories/" + directory).setExact(true));
    }
}
