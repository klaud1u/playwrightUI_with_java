package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class DigestAuthenticationPage extends BasePage {

    public DigestAuthenticationPage(Page page) {
        super(page);

    }
    public Locator header = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Digest Auth").setExact(true));
    public Locator successMessage = page.getByText("Congratulations! You must have the proper credentials.", new Page.GetByTextOptions().setExact(true)
    );
}
