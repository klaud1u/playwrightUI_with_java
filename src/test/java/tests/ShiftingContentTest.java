package tests;

import config.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.ShiftingContentPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
@Tag("regression")
public class ShiftingContentTest extends BaseTest{

    private ShiftingContentPage shiftingContentPage;

    @BeforeEach
    void setupPage() {
        shiftingContentPage = new ShiftingContentPage(page);
        mainPage.navigateToShiftingContent();
    }

    @Test
    @Tag("smoke")
    void weAreOnShiftingContentPage() {
        assertThat(page).hasURL(TestConfig.BASE_URL + "/shifting_content");
        assertThat(shiftingContentPage.header).hasText("Shifting Content");
        assertThat(shiftingContentPage.menuExampleLink).isVisible();
        assertThat(shiftingContentPage.imageExampleLink).isVisible();
        assertThat(shiftingContentPage.listExampleLink).isVisible();
    }

    @Test
    void menuExampleDisplaysAllLinks() {
        shiftingContentPage.menuExampleLink.click();
        assertThat(page).hasURL(TestConfig.BASE_URL + "/shifting_content/menu");
        assertThat(shiftingContentPage.header).hasText("Shifting Content: Menu Element");
        assertThat(shiftingContentPage.menuLinks).hasText(new String[]{"Home", "About", "Contact Us", "Portfolio", "Gallery"});
        assertThat(shiftingContentPage.galleryLink).isVisible();
    }

    @Test
    void galleryLinkRemainsAvailableAfterRefresh() {
        shiftingContentPage.menuExampleLink.click();
        assertThat(shiftingContentPage.galleryLink).isVisible();
        for (int i = 0; i < 5; i++) {
            page.reload();
            assertThat(shiftingContentPage.galleryLink).isVisible();
            assertThat(shiftingContentPage.galleryLink).hasText("Gallery");
        }
    }

    @Test
    void imageRemainsVisibleAfterRefresh() {
        shiftingContentPage.imageExampleLink.click();
        assertThat(page).hasURL(TestConfig.BASE_URL + "/shifting_content/image");
        assertThat(shiftingContentPage.header).hasText("Shifting Content: Image");
        assertThat(shiftingContentPage.shiftingImage).isVisible();
        assertThat(shiftingContentPage.shiftingImage).hasAttribute("src", "/img/avatar.jpg");
        for (int i = 0; i < 5; i++) {
            page.reload();
            assertThat(shiftingContentPage.shiftingImage).isVisible();
            assertThat(shiftingContentPage.shiftingImage).hasAttribute("src", "/img/avatar.jpg");
        }
    }

    @Test
    void importantInformationRemainsInListAfterRefresh() {
        shiftingContentPage.listExampleLink.click();
        assertThat(page).hasURL(TestConfig.BASE_URL + "/shifting_content/list");
        assertThat(shiftingContentPage.header).hasText("Shifting Content: List");
        assertThat(shiftingContentPage.listContent).containsText("Important Information You're Looking For");
        for (int i = 0; i < 5; i++) {
            page.reload();
            assertThat(shiftingContentPage.listContent).containsText("Important Information You're Looking For");
        }
    }
}

