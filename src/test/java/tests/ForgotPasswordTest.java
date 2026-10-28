package tests;


import config.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.BasePage;
import pages.ForgotPasswordPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
@Tag("regression")
public class ForgotPasswordTest extends BaseTest {

    private ForgotPasswordPage forgotPasswordPage;

    @BeforeEach
    void setupPage() {
        forgotPasswordPage = new ForgotPasswordPage(page);
        mainPage.navigateToForgotPassword();
    }

    @Test
    @Tag("smoke")
    void forgotPasswordPageDisplaysExpectedContent() {
        assertThat(page).hasURL(TestConfig.BASE_URL + "/forgot_password");
        assertThat(forgotPasswordPage.header).hasText("Forgot Password");
        assertThat(forgotPasswordPage.emailField).isVisible();
        assertThat(forgotPasswordPage.emailField).isEditable();
        assertThat(forgotPasswordPage.emailField).hasValue("");
        assertThat(forgotPasswordPage.retrievePasswordBtn).isVisible();
        assertThat(forgotPasswordPage.retrievePasswordBtn).isEnabled();
    }

    @Test
    void submitEmailAddressShowsSuccessMessage() {
        assertThat(forgotPasswordPage.header).isVisible();
        assertThat(forgotPasswordPage.emailField).isVisible();
        forgotPasswordPage.emailField.fill("claudiu@test.com");
        forgotPasswordPage.retrievePasswordBtn.click();
        assertThat(forgotPasswordPage.successMessage).isVisible();
    }

}
