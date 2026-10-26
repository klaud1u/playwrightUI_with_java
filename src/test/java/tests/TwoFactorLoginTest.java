package tests;

import config.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.BasePage;
import pages.TwoFactorLoginPage;
import testdata.LoginTestData;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class TwoFactorLoginTest extends BaseTest{

    private TwoFactorLoginPage twoFactorLoginPage;

    @BeforeEach
    void setupPage() {
        twoFactorLoginPage = new TwoFactorLoginPage(page);
        mainPage.twoFactorLoginLink.click();
    }

    @Test
    void loginPageDisplaysExpectedFields() {
        assertThat(twoFactorLoginPage.header).hasText("Two-Factor Login");
        assertThat(page).hasURL(TestConfig.BASE_URL + "/two_factor");
        assertThat(twoFactorLoginPage.username).isVisible();
        assertThat(twoFactorLoginPage.password).isVisible();
        assertThat(twoFactorLoginPage.nextBtn).isVisible();
    }

    @Test
    void validCredentialsOpenCodePage() {
        twoFactorLoginPage.login(LoginTestData.USERNAME, LoginTestData.PASSWORD);
        assertThat(twoFactorLoginPage.codeHeader).hasText("Enter Your Code");
        assertThat(page).hasURL(TestConfig.BASE_URL + "/two_factor" + "/code");
        assertThat(twoFactorLoginPage.displayedCode).hasText(Pattern.compile("^\\d{6}$"));
        assertThat(twoFactorLoginPage.codeInput).isVisible();
        assertThat(twoFactorLoginPage.verifyBtn).isVisible();
    }

    @ParameterizedTest
    @CsvSource({
            "wrongUser, SuperSecretPassword!",
            "tomsmith, wrongPass",
            "wrongUser, wrongPass"
    })
    void invalidCredentialsKeepUserOnLoginPage(String username, String password) {
        twoFactorLoginPage.login(username, password);
        assertThat(twoFactorLoginPage.statusMessage).containsText("Your username or password is invalid!");
        assertThat(twoFactorLoginPage.header).hasText("Two-Factor Login");
        assertThat(twoFactorLoginPage.username).isVisible();
        assertThat(twoFactorLoginPage.password).isVisible();
        assertThat(twoFactorLoginPage.codeInput).isHidden();
    }

    @Test
    void validCodeOpensSecureArea() {
        twoFactorLoginPage.login(LoginTestData.USERNAME, LoginTestData.PASSWORD);
        assertThat(twoFactorLoginPage.codeHeader).isVisible();
        String code = twoFactorLoginPage.displayedCode.textContent().trim();
        twoFactorLoginPage.verifyCode(code);
        assertThat(twoFactorLoginPage.secureAreaHeader).hasText("Secure Area");
        assertThat(twoFactorLoginPage.statusMessage).containsText("You logged in with two-factor authentication!");
        assertThat(page).hasURL(TestConfig.BASE_URL + "/two_factor" + "/secure");
        assertThat(twoFactorLoginPage.logoutBtn).isVisible();
    }

    @Test
    void invalidCodeShowsError() {
        twoFactorLoginPage.login(LoginTestData.USERNAME, LoginTestData.PASSWORD);
        assertThat(twoFactorLoginPage.codeHeader).isVisible();
        twoFactorLoginPage.verifyCode("123455");
        assertThat(twoFactorLoginPage.statusMessage).containsText("Your code is invalid!");
        assertThat(twoFactorLoginPage.codeHeader).hasText("Enter Your Code");
        assertThat(twoFactorLoginPage.codeInput).isVisible();
        assertThat(twoFactorLoginPage.secureAreaHeader).isHidden();
    }

    @Test
    void logoutReturnsToLoginPage() {
        twoFactorLoginPage.login(LoginTestData.USERNAME, LoginTestData.PASSWORD);
        assertThat(twoFactorLoginPage.codeHeader).isVisible();
        String code = twoFactorLoginPage.displayedCode.textContent().trim();
        twoFactorLoginPage.verifyCode(code);
        assertThat(twoFactorLoginPage.secureAreaHeader).isVisible();
        twoFactorLoginPage.logoutBtn.click();
        assertThat(twoFactorLoginPage.header).hasText("Two-Factor Login");
        assertThat(twoFactorLoginPage.username).isVisible();
        assertThat(twoFactorLoginPage.password).isVisible();
        assertThat(twoFactorLoginPage.nextBtn).isVisible();
    }
    @Test
    void expiredCodeReturnsToLoginPage() throws InterruptedException {
        twoFactorLoginPage.login(LoginTestData.USERNAME, LoginTestData.PASSWORD);
        assertThat(twoFactorLoginPage.codeHeader).isVisible();
        String code = twoFactorLoginPage.displayedCode.textContent().trim();
        assertThat(page).hasURL(TestConfig.BASE_URL + "/two_factor" + "/code");
        Thread.sleep(185_000);
        twoFactorLoginPage.verifyCode(code);
        assertThat(twoFactorLoginPage.header).hasText("Two-Factor Login");
        assertThat(twoFactorLoginPage.statusMessage).containsText("Your code has expired! Please log in again.");
        assertThat(twoFactorLoginPage.username).isVisible();
        assertThat(twoFactorLoginPage.password).isVisible();
        assertThat(twoFactorLoginPage.nextBtn).isVisible();
        assertThat(page).hasURL(TestConfig.BASE_URL + "/two_factor");
    }
}
