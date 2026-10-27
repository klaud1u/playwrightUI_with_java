package tests;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.BasePage;
import pages.ForgotPasswordPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ForgotPasswordTest extends BaseTest {

    private ForgotPasswordPage forgotPasswordPage;

    @BeforeEach
    void setupPage() {
        forgotPasswordPage = new ForgotPasswordPage(page);
        mainPage.forgotPasswordLink.click();
    }

    @Test
    void emailAddressFieldIsInitiallyEmpty() {
        assertThat(forgotPasswordPage.emailField).isEmpty();
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
