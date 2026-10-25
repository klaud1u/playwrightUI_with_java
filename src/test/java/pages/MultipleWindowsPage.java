package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class MultipleWindowsPage extends BasePage{
    public MultipleWindowsPage(Page page) {
        super(page);
    }

    public Locator header = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Opening a new window").setExact(true));
    public Locator clickHereLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Click Here").setExact(true));
    public Locator newWindowHeader = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("New Window").setExact(true));
}
