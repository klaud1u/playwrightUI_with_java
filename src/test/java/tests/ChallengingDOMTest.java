package tests;

import com.microsoft.playwright.Locator;
import config.TestConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.BrokenImagesPage;
import pages.ChallengingDOMPage;
import pages.MainPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Tag("regression")
public class ChallengingDOMTest  extends  BaseTest{

    private ChallengingDOMPage challengingDOMPage;

    @BeforeEach
    void setupPage() {
        challengingDOMPage = new ChallengingDOMPage(page);
        mainPage.navigateToChallengingDOM();
    }


    private void verifyCanvasChangesAfterClick(Locator button, int clickCount) {
        for (int i = 1; i <= clickCount; i++) {
            String beforeCanvas = (String) challengingDOMPage.canvas.evaluate("el => el.toDataURL()");
            button.click();
            String afterCanvas = (String) challengingDOMPage.canvas.evaluate("el => el.toDataURL()");
            Assertions.assertNotEquals(beforeCanvas, afterCanvas, "text didn't changed" + i);
        }
    }

    @Test
    @Tag("smoke")
    void pageDisplaysExpectedContent() {
        assertThat(page).hasURL(TestConfig.BASE_URL + "/challenging_dom");
        assertThat(challengingDOMPage.header).hasText("Challenging DOM");
        assertThat(challengingDOMPage.blackBtn).isVisible();
        assertThat(challengingDOMPage.orangeBtn).isVisible();
        assertThat(challengingDOMPage.greenBtn).isVisible();
        assertThat(challengingDOMPage.canvas).isVisible();
    }

    @Test
    @Tag("smoke")
void testChallengingDomBlackBtn(){
     verifyCanvasChangesAfterClick(challengingDOMPage.blackBtn, 5);
};
    @Test
void testChallengingDomOrangeBtn(){
        verifyCanvasChangesAfterClick(challengingDOMPage.orangeBtn, 6);
};
    @Test
void testChallengingDomGreenBtn(){
        verifyCanvasChangesAfterClick(challengingDOMPage.greenBtn, 5);
}
}


