package tests;

import config.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.FormAuthenticationPage;
import testdata.LoginTestData;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
@Tag("regression")
public class FormAuthenticationTest extends BaseTest{
    private FormAuthenticationPage formAuthenticationPage;

    @BeforeEach
    void setupPage() {
        formAuthenticationPage = new FormAuthenticationPage(page);
        mainPage.navigateToFormAuthentication();
    }

    @Test
    @Tag("smoke")
    void formAuthenticationPageDisplaysExpectedContent() {
        assertThat(page).hasURL(TestConfig.BASE_URL + "/login");
        assertThat(formAuthenticationPage.header).hasText("Login Page");
        assertThat(formAuthenticationPage.usernameField).isVisible();
        assertThat(formAuthenticationPage.usernameField).isEditable();
        assertThat(formAuthenticationPage.usernameField).hasValue("");
        assertThat(formAuthenticationPage.passwordField).isVisible();
        assertThat(formAuthenticationPage.passwordField).isEditable();
        assertThat(formAuthenticationPage.passwordField).hasValue("");
        assertThat(formAuthenticationPage.loginBtn).isVisible();
        assertThat(formAuthenticationPage.loginBtn).isEnabled();
    }

    @Test
    void validCredentialsOpenSecureArea() {
        formAuthenticationPage.login(LoginTestData.USERNAME, LoginTestData.PASSWORD);
        assertThat(formAuthenticationPage.secureAreaHeader).isVisible();
        assertThat(formAuthenticationPage.statusMessage).containsText("You logged into a secure area!");
        assertThat(formAuthenticationPage.logoutBtn).isVisible();
    }

    @Test
    void invalidUsernameShowsError() {
        formAuthenticationPage.login("test", LoginTestData.PASSWORD);
        assertThat(formAuthenticationPage.statusMessage).containsText("Your username is invalid!");}

    @Test
    void invalidPasswordShowsError() {
        formAuthenticationPage.login(LoginTestData.USERNAME, "test");
        assertThat(formAuthenticationPage.statusMessage).containsText("Your password is invalid!");}



    @Test
    void logoutReturnsToLoginPage() {
        formAuthenticationPage.login(LoginTestData.USERNAME, LoginTestData.PASSWORD);
        formAuthenticationPage.logoutBtn.click();
        assertThat(formAuthenticationPage.header).isVisible();
        assertThat(formAuthenticationPage.statusMessage).containsText("You logged out of the secure area!");
    }
}



