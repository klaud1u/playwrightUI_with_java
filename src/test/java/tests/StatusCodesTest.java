package tests;

import config.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.StatusCodesPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
@Tag("regression")
public class StatusCodesTest extends BaseTest {
    private StatusCodesPage statusCodesPage;

    @BeforeEach
    void setupPage() {
        statusCodesPage = new StatusCodesPage(page);
        mainPage.navigateToStatusCodes();
    }

    @Test
    @Tag("smoke")
    void pageDisplaysExpectedContent() {
        assertThat(page).hasURL(TestConfig.BASE_URL + "/status_codes");
        assertThat(statusCodesPage.header).hasText("Status Codes");
        assertThat(statusCodesPage.statusLinks).hasText(new String[]{"200", "301", "404", "500"});
        assertThat(statusCodesPage.statusCodeLink(200)).isVisible();
        assertThat(statusCodesPage.statusCodeLink(301)).isVisible();
        assertThat(statusCodesPage.statusCodeLink(404)).isVisible();
        assertThat(statusCodesPage.statusCodeLink(500)).isVisible();
    }

    @ParameterizedTest
    @ValueSource(ints = {200, 301, 404, 500})
    void clickingStatusCodeDisplaysExpectedPage(int statusCode) {
        statusCodesPage.statusCodeLink(statusCode).click();
        assertThat(page).hasURL(TestConfig.BASE_URL + "/status_codes/" + statusCode);
        assertThat(statusCodesPage.header).hasText("Status Codes");
        assertThat(statusCodesPage.message).containsText("This page returned a " + statusCode + " status code.");
        assertThat(statusCodesPage.backLink).isVisible();
    }

    @ParameterizedTest
    @ValueSource(ints = {200, 301, 404, 500})
    void backLinkReturnsToStatusCodesPage(int statusCode) {
        statusCodesPage.statusCodeLink(statusCode).click();
        assertThat(statusCodesPage.message).containsText("This page returned a " + statusCode + " status code.");
        statusCodesPage.backLink.click();
        assertThat(page).hasURL(TestConfig.BASE_URL + "/status_codes");
        assertThat(statusCodesPage.header).hasText("Status Codes");
        assertThat(statusCodesPage.statusLinks).hasText(new String[]{"200", "301", "404", "500"});
    }
}
