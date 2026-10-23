package pages;


import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class KeyPressesPage extends BasePage {
    public KeyPressesPage(Page page) {
        super(page);
    }

    public Locator header = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Key Presses").setExact(true));
    public Locator input = page.locator("#target");
    public Locator result = page.locator("#result");
}

