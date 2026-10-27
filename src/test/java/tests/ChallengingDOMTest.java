package tests;

import com.microsoft.playwright.Locator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.BrokenImagesPage;
import pages.ChallengingDOMPage;
import pages.MainPage;


public class ChallengingDOMTest  extends  BaseTest{

    private ChallengingDOMPage challengingDOMPage;

    @BeforeEach
    void setupPage() {
        challengingDOMPage = new ChallengingDOMPage(page);
        mainPage.challengingDOMLink.click();
    }


    private void verifyCanvasChangesAfterClick(Locator button, int clickCount) {
        for (int i = 1; i <= clickCount; i++) {
            String beforeCanvas = (String) challengingDOMPage.canvas.evaluate("el => el.toDataURL()");
            button.click();
            String afterCanvas = (String) challengingDOMPage.canvas.evaluate("el => el.toDataURL()");
            Assertions.assertNotEquals(beforeCanvas, afterCanvas, "Nu sa schimbat textul" + i);
        }
    }
    @Test
void testChallengingDomBlackBtn(){
     Assertions.assertEquals("Challenging DOM", challengingDOMPage.header.textContent().trim());
     verifyCanvasChangesAfterClick(challengingDOMPage.blackBtn, 5);
};
    @Test
void testChallengingDomOrangeBtn(){
        Assertions.assertEquals("Challenging DOM", challengingDOMPage.header.textContent().trim());
        verifyCanvasChangesAfterClick(challengingDOMPage.orangeBtn, 6);
};
    @Test
void testChallengingDomGreenBtn(){
        Assertions.assertEquals("Challenging DOM", challengingDOMPage.header.textContent().trim());
        verifyCanvasChangesAfterClick(challengingDOMPage.greenBtn, 5);
}
}


