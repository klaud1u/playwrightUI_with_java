package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class DisappearingElementsPage extends BasePage{

    public DisappearingElementsPage(Page page){
        super(page);
    }
    public Locator header = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Disappearing Elements"));
    public Locator homeLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Home"));
    public Locator aboutLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("About"));
    public Locator contactUsLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Contact Us"));
    public Locator portfolioLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Portfolio"));
    public Locator galleryLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Gallery"));
}
