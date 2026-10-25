package tests;

import org.junit.jupiter.api.Test;
import testdata.LoginTestData;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class FormAuthenticationTest extends BaseTest{

    @Test
    void validCredentialsOpenSecureArea() {
        mainPage.formAuthenticationLink.click();
        formAuthenticationPage.login(LoginTestData.USERNAME, LoginTestData.PASSWORD);
        assertThat(formAuthenticationPage.secureAreaHeader).isVisible();
        assertThat(formAuthenticationPage.statusMessage).containsText("You logged into a secure area!");
        assertThat(formAuthenticationPage.logoutBtn).isVisible();
    }

    @Test
    void invalidUsernameShowsError() {
        mainPage.formAuthenticationLink.click();
        formAuthenticationPage.login("test", LoginTestData.PASSWORD);
        assertThat(formAuthenticationPage.statusMessage).containsText("Your username is invalid!");}

    @Test
    void invalidPasswordShowsError() {
        mainPage.formAuthenticationLink.click();
        formAuthenticationPage.login(LoginTestData.USERNAME, "test");
        assertThat(formAuthenticationPage.statusMessage).containsText("Your password is invalid!");}



    @Test
    void logoutReturnsToLoginPage() {
        mainPage.formAuthenticationLink.click();
        formAuthenticationPage.login(LoginTestData.USERNAME, LoginTestData.PASSWORD);
        formAuthenticationPage.logoutBtn.click();
        assertThat(formAuthenticationPage.header).isVisible();
        assertThat(formAuthenticationPage.statusMessage).containsText("You logged out of the secure area!");
    }
}



