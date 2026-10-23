package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class ContextMenuPage extends BasePage {


    public ContextMenuPage(Page page) {
        super(page);
    }
    public Locator header = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Context Menu"));
    public Locator box =page.locator("#hot-spot");

}
