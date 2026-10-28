package tests;

import config.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.FloatingMenuPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
@Tag("regression")
public class FloatingMenuTest extends BaseTest{

    private FloatingMenuPage floatingMenuPage;

    @BeforeEach
    void setupPage() {
        floatingMenuPage = new FloatingMenuPage(page);
        mainPage.navigateToFloatingMenu();
    }

    @Test
    @Tag("smoke")
    void floatingMenuPageDisplaysExpectedContent() {
        assertThat(page).hasURL(TestConfig.BASE_URL + "/floating_menu");
        assertThat(floatingMenuPage.header).hasText("Floating Menu");
        assertThat(floatingMenuPage.floatingMenu).isVisible();
        assertThat(floatingMenuPage.homeLink).hasText("Home");
        assertThat(floatingMenuPage.newsLink).hasText("News");
        assertThat(floatingMenuPage.contactLink).hasText("Contact");
        assertThat(floatingMenuPage.aboutLink).hasText("About");
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

