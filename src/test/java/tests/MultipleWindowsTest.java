package tests;

import com.microsoft.playwright.Page;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.MultipleWindowsPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class MultipleWindowsTest extends BaseTest{

    private MultipleWindowsPage multipleWindowsPage;

    @BeforeEach
    void setupPage() {
        multipleWindowsPage = new MultipleWindowsPage(page);
        mainPage.multipleWindowsLink.click();
    }

    @Test
    void weAreOnMultipleWindowsPage() {
        assertThat(page).hasURL("https://the-internet.herokuapp.com/windows");
        assertThat(multipleWindowsPage.header).hasText("Opening a new window");
        assertThat(multipleWindowsPage.clickHereLink).isVisible();
    }

    @Test
    void clickingLinkOpensNewTabAndKeepsOriginalPage() {
        Page newTab = multipleWindowsPage.openNewTab(multipleWindowsPage.clickHereLink);
        MultipleWindowsPage newWindowPage = new MultipleWindowsPage(newTab);
        assertThat(newTab).hasURL("https://the-internet.herokuapp.com/windows/new");
        assertThat(newWindowPage.newWindowHeader).hasText("New Window");
        assertThat(newWindowPage.newWindowHeader).isVisible();
        assertThat(multipleWindowsPage.header).hasText("Opening a new window");
        Assertions.assertEquals(2, context.pages().size());
    }
    @Test
    void eachClickOpensANewTab() {
        for (int i = 1; i <= 3; i++) {
            Page newTab = multipleWindowsPage.openNewTab(multipleWindowsPage.clickHereLink);
            MultipleWindowsPage newWindowPage = new MultipleWindowsPage(newTab);
            newTab.bringToFront();
            assertThat(newTab).hasURL("https://the-internet.herokuapp.com/windows/new");
            assertThat(newWindowPage.newWindowHeader).hasText("New Window");
            assertThat(newWindowPage.newWindowHeader).isVisible();
            Assertions.assertEquals(i + 1, context.pages().size());
            page.bringToFront();
            assertThat(page).hasURL("https://the-internet.herokuapp.com/windows");
            assertThat(multipleWindowsPage.header).hasText("Opening a new window");
        }
    }

}
