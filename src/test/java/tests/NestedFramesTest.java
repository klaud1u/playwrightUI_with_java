package tests;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.NestedFramesPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
@Tag("regression")
public class NestedFramesTest extends BaseTest {

    private NestedFramesPage nestedFramesPage;

    @BeforeEach
    void setupPages() {
        nestedFramesPage = new NestedFramesPage(page);
        mainPage.navigateToNestedFrames();
    }

    @Test
    @Tag("smoke")
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
