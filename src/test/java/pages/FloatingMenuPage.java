package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class FloatingMenuPage extends MainPage {

    public FloatingMenuPage(Page page) {
        super(page);
    }

    public Locator header = page.getByRole(
            AriaRole.HEADING,
            new Page.GetByRoleOptions()
                    .setName("Floating Menu")
                    .setExact(true)
    );

    public Locator floatingMenu = page.locator("#menu");
    public Locator menuLinks = floatingMenu.getByRole(AriaRole.LINK);
    public Locator homeLink = floatingMenu.getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("Home").setExact(true));
    public Locator newsLink = floatingMenu.getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("News").setExact(true));
    public Locator contactLink = floatingMenu.getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("Contact").setExact(true));
    public Locator aboutLink = floatingMenu.getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("About").setExact(true));
    public Locator elementalSeleniumLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Elemental Selenium").setExact(true));
}
