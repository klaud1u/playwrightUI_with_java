package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.BrokenImagesPage;
import pages.MainPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


public class BrokenImagesTest extends BaseTest {

    private BrokenImagesPage brokenImagesPage;

    @BeforeEach
    void setupPage() {
        brokenImagesPage = new BrokenImagesPage(page);
        mainPage.brokenImagesLink.click();
    }

    @Test
    void brokenImagesTest() {
        Assertions.assertTrue(page.url().endsWith("/broken_images"));
        brokenImagesPage.waitForImagesToFinishLoading();
        assertThat(brokenImagesPage.images).hasCount(3);
        int brokenCount = ((Number) brokenImagesPage.images.evaluateAll("images => images.filter(img => img.naturalWidth === 0).length")).intValue();
        //System.out.println(brokenImagesPage.images.evaluateAll("images => images.map(img => `${img.src} | complete=${img.complete} | width=${img.naturalWidth}`)"));
        Assertions.assertEquals(2, brokenCount);
    }
    @Test
    void intendedBrokenImagesAreBroken() {
        brokenImagesPage.waitForImagesToFinishLoading();
        Assertions.assertEquals(0, ((Number) brokenImagesPage.firstBrokenImage.evaluate("img => img.naturalWidth")).intValue());
        Assertions.assertEquals(0, ((Number) brokenImagesPage.secondBrokenImage.evaluate("img => img.naturalWidth")).intValue());
    }

}
