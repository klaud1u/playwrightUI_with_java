package pages;


import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class FramesPage extends BasePage{
    public FramesPage(Page page) {
        super(page);
    }

    public Locator header = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Frames").setExact(true));
    public Locator nestedFramesLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Nested Frames").setExact(true));
    public Locator iframeLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("iFrame").setExact(true));
}
