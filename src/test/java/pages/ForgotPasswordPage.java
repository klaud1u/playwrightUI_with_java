package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class ForgotPasswordPage extends BasePage {
    public ForgotPasswordPage(Page page) {
        super(page);
    }

    public Locator header = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Forgot Password").setExact(true));
    public Locator emailField = page.getByLabel("E-mail");
    public Locator retrievePasswordBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Retrieve password").setExact(true));
    public Locator successMessage = page.getByText("Your e-mail's been sent!", new Page.GetByTextOptions().setExact(true));
}
