package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.FloatingMenuPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class FloatingMenuTest extends BaseTest{

    private FloatingMenuPage floatingMenuPage;

    @BeforeEach
    void setupPage() {
        floatingMenuPage = new FloatingMenuPage(page);
        mainPage.floatingMenuLink.click();
    }

    @Test
    void floatingMenuRemainsVisibleAfterScroll() {
        assertThat(floatingMenuPage.header).isVisible();
        assertThat(floatingMenuPage.floatingMenu).isVisible();
        floatingMenuPage.elementalSeleniumLink.scrollIntoViewIfNeeded();
        assertThat(floatingMenuPage.elementalSeleniumLink).isVisible();
        assertThat(floatingMenuPage.floatingMenu).isVisible();
        assertThat(floatingMenuPage.homeLink).isVisible();
        assertThat(floatingMenuPage.newsLink).isVisible();
        assertThat(floatingMenuPage.contactLink).isVisible();
        assertThat(floatingMenuPage.aboutLink).isVisible();
    }
}

