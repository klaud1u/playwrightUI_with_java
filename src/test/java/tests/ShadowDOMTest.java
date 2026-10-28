package tests;

import config.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.ShadowDOMPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
@Tag("regression")
public class ShadowDOMTest extends BaseTest{


    private ShadowDOMPage shadowDOMPage;

    @BeforeEach
    void setupPage() {
        shadowDOMPage = new ShadowDOMPage(page);
        mainPage.navigateToShadowDOM();
    }

    @Test
    @Tag("smoke")
    void shadowDOMPageDisplaysExpectedContent() {
        assertThat(page).hasURL(TestConfig.BASE_URL + "/shadowdom");
        assertThat(shadowDOMPage.header).hasText("Simple template");
        assertThat(shadowDOMPage.firstParagraph).isVisible();
        assertThat(shadowDOMPage.firstText).hasText("Let's have some different text!");
        assertThat(shadowDOMPage.secondParagraph).isVisible();
        assertThat(shadowDOMPage.listItems).hasText(new String[]{"Let's have some different text!", "In a list!"});
    }
}
