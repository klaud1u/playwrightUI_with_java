package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class GeolocationPage extends BasePage {
    public GeolocationPage(Page page) {
        super(page);
    }

    public Locator header = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Geolocation").setExact(true));
    public Locator locationMessage = page.locator("#demo");
    public Locator whereAmIBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Where am I?").setExact(true));
    public Locator latitude = page.locator("#lat-value");
    public Locator longitude = page.locator("#long-value");
    public Locator googleMapsLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("See it on Google").setExact(true)
    );
}
