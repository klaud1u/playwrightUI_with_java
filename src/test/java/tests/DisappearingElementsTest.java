package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DisappearingElementsTest extends BaseTest{
    @Test
    void galleryCanAppearAndDisappearAfterTenRefreshes() {
        mainPage.disappearingElementsLink.click();
        boolean galleryAppeared = disappearingElementsPage.galleryLink.count() == 1;
        boolean galleryWasAbsent = !galleryAppeared;
        for (int i = 0; i < 9; i++) {
            page.reload();
            if (disappearingElementsPage.galleryLink.count() == 1) {
                galleryAppeared = true;
            }
            else {
                galleryWasAbsent = true;
            }
        }
        Assertions.assertTrue(galleryAppeared);
        Assertions.assertTrue(galleryWasAbsent);
    }
}

