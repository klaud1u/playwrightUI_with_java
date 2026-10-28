package tests;

import com.microsoft.playwright.Locator;
import config.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.CanvasPage;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
@Tag("regression")
public class CanvasTest extends BaseTest {

    private CanvasPage canvasPage;

    @BeforeEach
    void setupPage() {
        canvasPage = new CanvasPage(page);
        mainPage.navigateToCanvas();
    }

    @Test
    @Tag("smoke")
    void canvasPageDisplaysExpectedContent() {
        assertThat(page).hasURL(TestConfig.BASE_URL + "/canvas");
        assertThat(canvasPage.header).hasText("Canvas");
        assertThat(canvasPage.canvas).isVisible();
        assertThat(canvasPage.submittedMsg).hasText("Nothing yet");
    }

    @Test
    @Tag("smoke")
    void clickingCanvasDisplaysCorrectCoordinates() throws InterruptedException {
        canvasPage.clickAt(100, 150);
        assertThat(canvasPage.submittedMsg).hasText("Clicked at 101, 151");
    }

    @Test
    @Tag("smoke")
    void typedTextCanBeSubmitted() {
        canvasPage.clickCanvas();
        canvasPage.typeText("test test");
        canvasPage.submitText();
        assertThat(canvasPage.submittedMsg).hasText("Submitted: test test");
    }

    @Test
    void backspaceRemovesLastCharacter() {
        canvasPage.clickCanvas();
        canvasPage.typeText("test1");
        canvasPage.removeLastCharacter();
        canvasPage.submitText();
        assertThat(canvasPage.submittedMsg).hasText("Submitted: test");
    }

    @Test
    void submittedTextIsClearedBeforeNextEntry() {
        canvasPage.clickCanvas();
        canvasPage.typeText("test1");
        canvasPage.submitText();
        assertThat(canvasPage.submittedMsg).hasText("Submitted: test1");
        canvasPage.typeText("test2");
        canvasPage.submitText();
        assertThat(canvasPage.submittedMsg).hasText("Submitted: test2");
    }

}

