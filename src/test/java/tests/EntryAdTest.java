package tests;

import com.microsoft.playwright.Locator;
import config.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pages.EntryAdPage;
import pages.MainPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class EntryAdTest extends BaseTest{
    private EntryAdPage entryAdPage;


    @BeforeEach
    void setupPage() {
        entryAdPage = new EntryAdPage(page);
        mainPage.entryAdLink.click();
    }

    @Test
    void enteringPageDisplaysAd() {
        assertThat(page).hasURL(TestConfig.BASE_URL + "/entry_ad");
        assertThat(entryAdPage.header).hasText("Entry Ad");
        assertThat(entryAdPage.modal).isVisible();
        assertThat(entryAdPage.modalHeader).hasText("This is a modal window");
        assertThat(entryAdPage.modalBody).isVisible();
        assertThat(entryAdPage.closeLink).isVisible();
        assertThat(entryAdPage.footer).isHidden();
    }

    @Test
    void clickingCloseHidesAd() {
        assertThat(entryAdPage.modal).isVisible();
        entryAdPage.closeModal();
        assertThat(entryAdPage.modal).isHidden();
        assertThat(entryAdPage.footer).isVisible();
        assertThat(entryAdPage.restartAdLink).isVisible();
    }

    @Test
    void clickingOutsideAdClosesIt() {
        assertThat(entryAdPage.modal).isVisible();
        entryAdPage.modalBackground.click(new Locator.ClickOptions().setPosition(10, 10));
        assertThat(entryAdPage.modal).isHidden();
        assertThat(entryAdPage.footer).isVisible();
    }

    @Test
    void clickingInsideAdKeepsItOpen() {
        assertThat(entryAdPage.modal).isVisible();
        entryAdPage.modalBody.click();
        assertThat(entryAdPage.modal).isVisible();
        assertThat(entryAdPage.footer).isHidden();
    }

    @Test
    void closedAdDoesNotReappearAfterRefresh() {
        assertThat(entryAdPage.modal).isVisible();
        entryAdPage.closeModal();
        assertThat(entryAdPage.modal).isHidden();
        page.reload();
        assertThat(entryAdPage.header).hasText("Entry Ad");
        assertThat(entryAdPage.modal).isHidden();
        assertThat(entryAdPage.footer).isVisible();
    }

    @Disabled ("the request sometimes returns 404")
    @Test
    void restartLinkDisplaysAdAgain() {
        assertThat(entryAdPage.modal).isVisible();
        entryAdPage.closeModal();
        assertThat(entryAdPage.modal).isHidden();
        entryAdPage.restartAdLink.click();
        assertThat(entryAdPage.modal).isVisible();
        assertThat(entryAdPage.modalHeader).hasText("This is a modal window");
        assertThat(entryAdPage.footer).isHidden();
    }
}
