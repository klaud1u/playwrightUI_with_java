package tests;

import config.TestConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.BrokenImagesPage;
import pages.MainPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Tag("regression")
public class BrokenImagesTest extends BaseTest {

    private BrokenImagesPage brokenImagesPage;

    @BeforeEach
    void setupPage() {
        brokenImagesPage = new BrokenImagesPage(page);
        mainPage.navigateToBrokenImages();
    }

    @Test
    @Tag("smoke")
    void pageContainsTwoBrokenImages() {
        assertThat(page).hasURL(TestConfig.BASE_URL + "/broken_images");
        brokenImagesPage.waitForImagesToFinishLoading();
        assertThat(brokenImagesPage.images).hasCount(3);
        Assertions.assertEquals(2, brokenImagesPage.brokenImagesCount());
    }
    @Test
    void intendedBrokenImagesAreBroken() {
        brokenImagesPage.waitForImagesToFinishLoading();
        Assertions.assertEquals(0, ((Number) brokenImagesPage.firstBrokenImage.evaluate("img => img.naturalWidth")).intValue());
        Assertions.assertEquals(0, ((Number) brokenImagesPage.secondBrokenImage.evaluate("img => img.naturalWidth")).intValue());
    }

}
