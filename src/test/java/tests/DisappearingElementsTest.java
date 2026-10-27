package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.DisappearingElementsPage;

public class DisappearingElementsTest extends BaseTest{

    private DisappearingElementsPage disappearingElementsPage;

    @BeforeEach
    void setupPage() {
        disappearingElementsPage = new DisappearingElementsPage(page);
        mainPage.disappearingElementsLink.click();
    }
    @Test
    void galleryCanAppearAndDisappearAfterTenRefreshes() {

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

