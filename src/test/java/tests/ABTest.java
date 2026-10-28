package tests;

import config.TestConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.ABPage;
import pages.BasicAuthPage;
import pages.GetByTextPage;
import pages.MainPage;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static java.awt.SystemColor.text;
@Tag("regression")
public class ABTest extends BaseTest{

    private ABPage abPage;

    @BeforeEach
    void setupPage() {
        abPage = new ABPage(page);
        mainPage.AbTestingLink.click();
    }
    @Test
    @Tag("smoke")
    void abTestingPageDisplaysExpectedContent() {
        assertThat(page).hasURL(TestConfig.BASE_URL + "/abtest");
        assertThat(abPage.header).hasText(Pattern.compile("^A/B Test (Control|Variation 1)$"));
        assertThat(abPage.text).containsText("Also known as split testing.");
    }
}
