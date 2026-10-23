package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class JqueryUIMenusPage extends BasePage {
        public JqueryUIMenusPage(Page page) {
            super(page);
        }

    public Locator header = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("JQueryUI - Menu").setExact(true));
    public Locator menu = page.locator("#menu");
    public Locator disabledItem = page.locator("#menu > li.ui-state-disabled");
    public Locator disabledSubmenu = disabledItem.locator("ul[role='menu']");
    public Locator enabledLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Enabled").setExact(true));
    public Locator downloadsLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Downloads").setExact(true));
    public Locator pdfLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("PDF").setExact(true));
    public Locator csvLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("CSV").setExact(true));
    public Locator excelLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Excel").setExact(true));
    public Locator backToJQueryUILink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Back to JQuery UI").setExact(true));

    public Locator menuLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Menu").setExact(true));
    public Locator jqueryUIHeading = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("JQuery UI").setExact(true));

    public void openDownloadsSubmenu() {
        enabledLink.hover();
        downloadsLink.hover();
    }
}

