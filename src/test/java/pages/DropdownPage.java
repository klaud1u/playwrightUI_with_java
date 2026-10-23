package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class DropdownPage extends BasePage {


    public DropdownPage(Page page) {
        super(page);
    }

    public Locator header = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Dropdown List"));
    public Locator dropdown = page.locator("#dropdown");
}
