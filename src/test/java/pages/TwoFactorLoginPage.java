package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class TwoFactorLoginPage extends BasePage {
    public TwoFactorLoginPage(Page page){
        super(page);
    }

    public Locator header = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Two-Factor Login").setExact(true));
    public Locator username = page.getByLabel("Username");
    public Locator password = page.getByLabel("Password");
    public Locator nextBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Next").setExact(true));
    public Locator codeHeader = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Enter Your Code").setExact(true));
    public Locator displayedCode = page.locator("#otp-code");
    public Locator codeInput = page.getByLabel("One-time code");
    public Locator verifyBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Verify").setExact(true));
    public Locator statusMessage = page.locator("#flash");
    public Locator secureAreaHeader = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Secure Area").setExact(true)
    );

    public Locator logoutBtn= page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Logout").setExact(true));

    public void login(String usernameValue, String passwordValue) {
        username.fill(usernameValue);
        password.fill(passwordValue);
        nextBtn.click();
    }

    public void verifyCode(String code) {
        codeInput.fill(code);
        verifyBtn.click();
    }
}
