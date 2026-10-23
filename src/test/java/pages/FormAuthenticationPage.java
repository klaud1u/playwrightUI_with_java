package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class FormAuthenticationPage extends BasePage{
    public FormAuthenticationPage(Page page) {
        super(page);
    }

    public Locator header = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Login Page").setExact(true));
    public Locator usernameField = page.getByLabel("Username");
    public Locator passwordField = page.getByLabel("Password");
    public Locator loginBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login"));
    public Locator statusMessage = page.locator("#flash");
    public Locator secureAreaHeader = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Secure Area").setExact(true));
    public Locator logoutBtn = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Logout"));

    public void login(String username, String password) {
        usernameField.fill(username);
        passwordField.fill(password);
        loginBtn.click();
    }
}
