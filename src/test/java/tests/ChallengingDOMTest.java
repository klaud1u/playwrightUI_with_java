package tests;

import com.microsoft.playwright.Locator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.ChallengingDOMPage;
import pages.MainPage;


public class ChallengingDOMTest  extends  BaseTest{
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
     mainPage.challengingDOMLink.click();
     Assertions.assertEquals("Challenging DOM", challengingDOMPage.header.textContent().trim());
     verifyCanvasChangesAfterClick(challengingDOMPage.blackBtn, 5);
};
    @Test
void testChallengingDomOrangeBtn(){
        mainPage.challengingDOMLink.click();
        Assertions.assertEquals("Challenging DOM", challengingDOMPage.header.textContent().trim());
        verifyCanvasChangesAfterClick(challengingDOMPage.orangeBtn, 6);
};
    @Test
void testChallengingDomGreenBtn(){
        mainPage.challengingDOMLink.click();
        Assertions.assertEquals("Challenging DOM", challengingDOMPage.header.textContent().trim());
        verifyCanvasChangesAfterClick(challengingDOMPage.greenBtn, 5);
}
}


