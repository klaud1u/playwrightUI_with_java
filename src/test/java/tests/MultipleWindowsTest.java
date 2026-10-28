package tests;

import com.microsoft.playwright.Page;
import config.TestConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.MultipleWindowsPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
@Tag("regression")
public class MultipleWindowsTest extends BaseTest{

    private MultipleWindowsPage multipleWindowsPage;

    @BeforeEach
    void setupPage() {
        multipleWindowsPage = new MultipleWindowsPage(page);
        mainPage.navigateToMultipleWindows();
    }

    @Test
    @Tag("smoke")
    void weAreOnMultipleWindowsPage() {
        assertThat(page).hasURL(TestConfig.BASE_URL + "/windows");
        assertThat(multipleWindowsPage.header).hasText("Opening a new window");
        assertThat(multipleWindowsPage.clickHereLink).isVisible();
    }

    @Test
    void clickingLinkOpensNewTabAndKeepsOriginalPage() {
            Page newTab = multipleWindowsPage.openNewTab(multipleWindowsPage.clickHereLink);
            MultipleWindowsPage newWindowPage = new MultipleWindowsPage(newTab);
            assertThat(newTab).hasURL(TestConfig.BASE_URL + "/windows/new");
            assertThat(newWindowPage.newWindowHeader).hasText("New Window");
            assertThat(newWindowPage.newWindowHeader).isVisible();
            assertThat(page).hasURL(TestConfig.BASE_URL + "/windows");
            assertThat(multipleWindowsPage.header).hasText("Opening a new window");
            assertThat(multipleWindowsPage.clickHereLink).isVisible();
            Assertions.assertEquals(2, context.pages().size());
    }
    @Test
    void eachClickOpensANewTab() {
        for (int i = 1; i <= 3; i++) {
            Page newTab = multipleWindowsPage.openNewTab(multipleWindowsPage.clickHereLink);
            MultipleWindowsPage newWindowPage = new MultipleWindowsPage(newTab);
            newTab.bringToFront();
            assertThat(newTab).hasURL(TestConfig.BASE_URL + "/windows/new");
            assertThat(newWindowPage.newWindowHeader).hasText("New Window");
            assertThat(newWindowPage.newWindowHeader).isVisible();
            Assertions.assertEquals(i + 1, context.pages().size());
            page.bringToFront();
            assertThat(page).hasURL(TestConfig.BASE_URL + "/windows");
            assertThat(multipleWindowsPage.header).hasText("Opening a new window");
            assertThat(multipleWindowsPage.clickHereLink).isVisible();
        }
    }

}
