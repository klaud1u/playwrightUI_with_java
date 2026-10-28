package tests;

import config.TestConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.TyposPage;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
@Tag("regression")
public class TyposTest extends BaseTest{
    private TyposPage typosPage;

    @BeforeEach
    void setupPage() {
        typosPage = new TyposPage(page);
        mainPage.navigateToTypos();
    }

    @Test
    @Tag("smoke")
    void pageDisplaysExpectedContent() {
        assertThat(page).hasURL(TestConfig.BASE_URL + "/typos");
        assertThat(typosPage.header).hasText("Typos");
        assertThat(typosPage.description).hasText("This example demonstrates a typo being introduced. " + "It does it randomly on each page load.");
        assertThat(typosPage.typoText).isVisible();
    }
    @Test
    void textDisplaysExpectedVariantAfter10Refresh() {
        Pattern expectedText = Pattern.compile("Sometimes you'll see a typo, other times you (won't|won,t)\\.");
        for (int i = 0; i < 10; i++) {
            if (i > 0) {
                page.reload();
            }
            assertThat(typosPage.typoText).hasText(expectedText);
        }
    }

}
