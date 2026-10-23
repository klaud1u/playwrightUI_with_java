package tests;

import com.microsoft.playwright.Locator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class DynamicContentTest extends BaseTest {
    @Test
    void allImagesAreVisibleAndLoaded() {
        mainPage.dynamicContentLink.click();
        assertThat(dynamicContentPage.header).isVisible();
        assertThat(dynamicContentPage.avatars).hasCount(3);
        dynamicContentPage.waitForImagesToFinishLoading();
        for (int i = 0; i < dynamicContentPage.avatars.count(); i++) {
            Locator img = dynamicContentPage.avatars.nth(i);
            assertThat(img).isVisible();
            boolean loaded = (boolean) img.evaluate("img => img.complete && img.naturalWidth > 0");
            Assertions.assertTrue(loaded);
        }
    }

}
