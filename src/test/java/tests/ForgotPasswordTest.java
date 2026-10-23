package tests;


import org.junit.jupiter.api.Test;
import pages.BasePage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ForgotPasswordTest extends BaseTest {

    @Test
    void emailAddressFieldIsInitiallyEmpty() {
        mainPage.forgotPasswordLink.click();
        assertThat(forgotPasswordPage.emailField).isEmpty();
    }

    @Test
    void submitEmailAddressShowsSuccessMessage() {
        mainPage.forgotPasswordLink.click();
        assertThat(forgotPasswordPage.header).isVisible();
        assertThat(forgotPasswordPage.emailField).isVisible();
        forgotPasswordPage.emailField.fill("claudiu@test.com");
        forgotPasswordPage.retrievePasswordBtn.click();
        assertThat(forgotPasswordPage.successMessage).isVisible();
    }

}
