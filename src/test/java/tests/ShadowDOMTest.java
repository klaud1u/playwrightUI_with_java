package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.ShadowDOMPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ShadowDOMTest extends BaseTest{


    private ShadowDOMPage shadowDOMPage;

    @BeforeEach
    void setupPage() {
        shadowDOMPage = new ShadowDOMPage(page);
        mainPage.shadowDOMLink.click();
    }

    @Test
    void shadowDOMPageDisplaysExpectedContent() {
        assertThat(shadowDOMPage.header).hasText("Simple template");
        assertThat(shadowDOMPage.firstParagraph).isVisible();
        assertThat(shadowDOMPage.firstText).hasText("Let's have some different text!");
        assertThat(shadowDOMPage.secondParagraph).isVisible();
        assertThat(shadowDOMPage.listItems).hasText(new String[]{"Let's have some different text!", "In a list!"});
    }
}
