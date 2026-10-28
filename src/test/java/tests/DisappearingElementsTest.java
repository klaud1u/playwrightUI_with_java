package tests;

import config.TestConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.DisappearingElementsPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
@Tag("regression")
public class DisappearingElementsTest extends BaseTest{

    private DisappearingElementsPage disappearingElementsPage;

    @BeforeEach
    void setupPage() {
        disappearingElementsPage = new DisappearingElementsPage(page);
        mainPage.navigateToDisappearingElements();
    }

    @Test
    @Tag("smoke")
    void pageDisplaysExpectedContent() {
        assertThat(page).hasURL(TestConfig.BASE_URL + "/disappearing_elements");
        assertThat(disappearingElementsPage.header).hasText("Disappearing Elements");
        assertThat(disappearingElementsPage.homeLink).isVisible();
        assertThat(disappearingElementsPage.aboutLink).isVisible();
        assertThat(disappearingElementsPage.contactUsLink).isVisible();
        assertThat(disappearingElementsPage.portfolioLink).isVisible();
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

