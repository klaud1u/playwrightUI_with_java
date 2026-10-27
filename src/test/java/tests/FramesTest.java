package tests;

import config.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.FramesPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class FramesTest extends BaseTest{

    private FramesPage framesPage;

    @BeforeEach
    void setupPage() {
        framesPage = new FramesPage(page);
        mainPage.framesLink.click();
    }
    // Additional scenarios are covered in NestedFramesTest and EditorTest (WYSIWYG Editor).
    @Test
    void weAreOnFramesPage() {
        assertThat(page).hasURL(TestConfig.BASE_URL + "/frames");
        assertThat(framesPage.header).hasText("Frames");
        assertThat(framesPage.nestedFramesLink).isVisible();
        assertThat(framesPage.iframeLink).isVisible();
    }

    @Test
    void clickingNestedFramesOpensNestedFramesPage() {
        framesPage.nestedFramesLink.click();
        assertThat(page).hasURL(TestConfig.BASE_URL + "/nested_frames");}

    @Test
    void clickingIframeOpensIframePage() {
        framesPage.iframeLink.click();
        assertThat(page).hasURL(TestConfig.BASE_URL + "/iframe");}
}
