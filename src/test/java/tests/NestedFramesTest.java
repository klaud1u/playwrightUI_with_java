package tests;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.NestedFramesPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class NestedFramesTest extends BaseTest {

    private NestedFramesPage nestedFramesPage;

    @BeforeEach
    void setupPages() {
        nestedFramesPage = new NestedFramesPage(page);
        mainPage.nestedFramesLink.click();
    }

    @Test
    void leftFrameDisplaysExpectedText() {
        assertThat(nestedFramesPage.leftText).hasText("LEFT");
        assertThat(nestedFramesPage.leftText).isVisible();
    }

    @Test
    void middleFrameDisplaysExpectedText() {
        assertThat(nestedFramesPage.middleText).hasText("MIDDLE");
        assertThat(nestedFramesPage.middleText).isVisible();
    }

    @Test
    void rightFrameDisplaysExpectedText() {
        assertThat(nestedFramesPage.rightText).hasText("RIGHT");
        assertThat(nestedFramesPage.rightText).isVisible();
    }

    @Test
    void bottomFrameDisplaysExpectedText() {
        assertThat(nestedFramesPage.bottomText).hasText("BOTTOM");
        assertThat(nestedFramesPage.bottomText).isVisible();
    }

}
