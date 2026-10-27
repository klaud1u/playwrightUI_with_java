package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class ShiftingContentPage extends BasePage {
    public ShiftingContentPage(Page page) {
        super(page);
    }


    public Locator header = page.locator(".example h3");

    public Locator menuExampleLink = page.getByRole(
            AriaRole.LINK,
            new Page.GetByRoleOptions()
                    .setName("Example 1: Menu Element")
                    .setExact(true)
    );

    public Locator imageExampleLink = page.getByRole(
            AriaRole.LINK,
            new Page.GetByRoleOptions()
                    .setName("Example 2: An image")
                    .setExact(true)
    );

    public Locator listExampleLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Example 3: List").setExact(true));

    // Menu
    public Locator menuLinks = page.locator(".example ul li a");
    public Locator galleryLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Gallery").setExact(true));

    // Image
    public Locator shiftingImage = page.locator(".example img.shift");

    // List
    public Locator listContent = page.locator(".example .large-6.columns.large-centered");
}
