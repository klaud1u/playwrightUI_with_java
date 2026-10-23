package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.BrokenImagesPage;
import pages.MainPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


public class BrokenImagesTest extends BaseTest {

    @Test
    void brokenImagesTest() {
        mainPage.brokenImagesLink.click();
        Assertions.assertTrue(page.url().endsWith("/broken_images"));
        brokenImagesPage.waitForImagesToFinishLoading();
        assertThat(brokenImagesPage.images).hasCount(3);
        int brokenCount = ((Number) brokenImagesPage.images.evaluateAll("images => images.filter(img => img.naturalWidth === 0).length")).intValue();
        //System.out.println(brokenImagesPage.images.evaluateAll("images => images.map(img => `${img.src} | complete=${img.complete} | width=${img.naturalWidth}`)"));
        Assertions.assertEquals(2, brokenCount);
    }
    @Test
    void intendedBrokenImagesAreBroken() {
        mainPage.brokenImagesLink.click();
        brokenImagesPage.waitForImagesToFinishLoading();
        Assertions.assertEquals(0, ((Number) brokenImagesPage.firstBrokenImage.evaluate("img => img.naturalWidth")).intValue());
        Assertions.assertEquals(0, ((Number) brokenImagesPage.secondBrokenImage.evaluate("img => img.naturalWidth")).intValue());
    }

}
