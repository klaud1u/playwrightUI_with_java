package tests;

import com.microsoft.playwright.Locator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.CanvasPage;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CanvasTest extends BaseTest {

    private CanvasPage canvasPage;

    @BeforeEach
    void setupPage() {
        canvasPage = new CanvasPage(page);
        mainPage.canvasLink.click();
    }

    @Test
    void canvasPageDisplaysExpectedContent() {
        assertThat(canvasPage.header).hasText("Canvas");
        assertThat(canvasPage.canvas).isVisible();
        assertThat(canvasPage.submittedMsg).hasText("Nothing yet");
    }

    @Test
    void clickingCanvasDisplaysCorrectCoordinates() throws InterruptedException {
        canvasPage.canvas.click(new Locator.ClickOptions().setPosition(100, 150));
        assertThat(canvasPage.submittedMsg).hasText("Clicked at 101, 151");
    }

    @Test
    void typedTextCanBeSubmitted() {
        canvasPage.canvas.click();
        canvasPage.canvas.pressSequentially("test test");
        canvasPage.canvas.press("Enter");
        assertThat(canvasPage.submittedMsg).hasText("Submitted: test test");
    }

    @Test
    void backspaceRemovesLastCharacter() {
        canvasPage.canvas.click();
        canvasPage.canvas.pressSequentially("test1");
        canvasPage.canvas.press("Backspace");
        canvasPage.canvas.press("Enter");
        assertThat(canvasPage.submittedMsg).hasText("Submitted: test");
    }

    @Test
    void submittedTextIsClearedBeforeNextEntry() {
        canvasPage.canvas.click();
        canvasPage.canvas.pressSequentially("test1");
        canvasPage.canvas.press("Enter");
        assertThat(canvasPage.submittedMsg).hasText("Submitted: test1");
        canvasPage.canvas.pressSequentially("test2");
        canvasPage.canvas.press("Enter");
        assertThat(canvasPage.submittedMsg).hasText("Submitted: test2");
    }

}

