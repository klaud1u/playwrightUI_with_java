package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class InfinteScrollTest extends BaseTest{
    @Test
    void scrollingLoadsMoreContent() {
        mainPage.infiniteScrollLink.click();
        assertThat(infiniteScrollPage.header).isVisible();

        int blocksBefore = infiniteScrollPage.textParagraph.count();

        infiniteScrollPage.textParagraph.last().scrollIntoViewIfNeeded();

        page.waitForFunction(
                "(count) => document.querySelectorAll('.scroll .jscroll-added').length > count",
                blocksBefore
        );
        System.out.println();
        Assertions.assertTrue(infiniteScrollPage.textParagraph.count() > blocksBefore);
    }
}
