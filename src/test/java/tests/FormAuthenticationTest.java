package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.FormAuthenticationPage;
import testdata.LoginTestData;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class FormAuthenticationTest extends BaseTest{
    private FormAuthenticationPage formAuthenticationPage;

    @BeforeEach
    void setupPage() {
        formAuthenticationPage = new FormAuthenticationPage(page);
        mainPage.formAuthenticationLink.click();
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



