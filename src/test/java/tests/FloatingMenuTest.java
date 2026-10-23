package tests;

import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class FloatingMenuTest extends BaseTest{
    @Test
    void floatingMenuRemainsVisibleAfterScroll() {
        mainPage.floatingMenuLink.click();
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

