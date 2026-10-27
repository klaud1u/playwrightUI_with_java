package tests;

import com.microsoft.playwright.assertions.LocatorAssertions;
import config.TestConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.InfiniteScrollPage;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class InfinteScrollTest extends BaseTest{

    private InfiniteScrollPage infiniteScrollPage;

    @BeforeEach
    void setupPage() {
        infiniteScrollPage = new InfiniteScrollPage(page);
        mainPage.infiniteScrollLink.click();
    }

    @Test
    void weAreOnInfiniteScrollPage() {
        assertThat(page).hasURL(TestConfig.BASE_URL + "/infinite_scroll");
        assertThat(infiniteScrollPage.header).hasText("Infinite Scroll");
        assertThat(infiniteScrollPage.textParagraph.first()).isVisible();
        assertThat(infiniteScrollPage.textParagraph.first()).not().isEmpty();
    }


    @Test
    void scrollingLoadsMoreParagraphs() {
        assertThat(infiniteScrollPage.textParagraph.first()).isVisible();
        int paragraphsBefore = infiniteScrollPage.textParagraph.count();
        infiniteScrollPage.scrollToBottom(30);
        assertThat(infiniteScrollPage.textParagraph.nth(paragraphsBefore + 30)).isAttached();
    }
}


