package tests;

import config.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.RedirectLinkPage;
import pages.StatusCodesPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class RedirectLinkTest extends BaseTest{

    private RedirectLinkPage redirectLinkPage;
    private StatusCodesPage statusCodesPage;

    @BeforeEach
    void setupPage() {
        redirectLinkPage = new RedirectLinkPage(page);
        statusCodesPage = new StatusCodesPage(page);
        mainPage.redirectLink.click();
    }

    @Test
    void weAreOnRedirectionPage() {
        assertThat(page).hasURL(TestConfig.BASE_URL + "/redirector");
        assertThat(redirectLinkPage.header).hasText("Redirection");
        assertThat(redirectLinkPage.clickHereLink).isVisible();
    }

    @Test
    void clickingHereRedirectsToStatusCodesPage() {
        assertThat(redirectLinkPage.clickHereLink).hasAttribute("href", "redirect");
        redirectLinkPage.clickHereLink.click();
        assertThat(page).hasURL(TestConfig.BASE_URL + "/status_codes");
        assertThat(statusCodesPage.header).hasText("Status Codes");
    }
}
