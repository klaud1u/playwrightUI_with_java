package tests;

import com.microsoft.playwright.Locator;
import config.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.ExitIntentPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ExistIntentTest extends BaseTest{

    private ExitIntentPage exitIntentPage;

    @BeforeEach
    void setupPage() {
        exitIntentPage = new ExitIntentPage(page);
        mainPage.exitIntentLink.click();
    }

    @Test
    void pageInitiallyDisplaysWithoutModal() {
        assertThat(page).hasURL(TestConfig.BASE_URL + "/exit_intent");
        assertThat(exitIntentPage.header).hasText("Exit Intent");
        assertThat(exitIntentPage.modal).isHidden();
        assertThat(exitIntentPage.footer).isVisible();
    }

    @Test
    void movingMouseOutsidePageDisplaysModal() {
        exitIntentPage.moveMouseOutsidePage();
        assertThat(exitIntentPage.modal).isVisible();
        assertThat(exitIntentPage.modalHeader).hasText("This is a modal window");
        assertThat(exitIntentPage.modalBody).hasText("It's commonly used to encourage a user to take an action " + "(e.g., give their e-mail address to sign up for something).");
        assertThat(exitIntentPage.closeLink).isVisible();
        assertThat(exitIntentPage.footer).isHidden();
    }

    @Test
    void clickingCloseHideModal() {
        exitIntentPage.moveMouseOutsidePage();
        assertThat(exitIntentPage.modal).isVisible();
        exitIntentPage.closeLink.click();
        assertThat(exitIntentPage.modal).isHidden();
        assertThat(exitIntentPage.footer).isVisible();
    }

    @Test
    void clickingOutsideModalCloseTheModal() {
        exitIntentPage.moveMouseOutsidePage();
        assertThat(exitIntentPage.modal).isVisible();
        exitIntentPage.modalBackground.click(new Locator.ClickOptions().setPosition(10, 10));
        assertThat(exitIntentPage.modal).isHidden();
        assertThat(exitIntentPage.footer).isVisible();
    }

    @Test
    void clickingInsideModalKeepsItOpen() {
        exitIntentPage.moveMouseOutsidePage();
        assertThat(exitIntentPage.modal).isVisible();
        exitIntentPage.modalBody.click();
        assertThat(exitIntentPage.modal).isVisible();
        assertThat(exitIntentPage.modalHeader).hasText("This is a modal window");
        assertThat(exitIntentPage.footer).isHidden();
    }
}
