package tests;

import com.microsoft.playwright.Download;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.JqueryUIMenusPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
@Tag("regression")
public class JqueryUIMenusTest extends BaseTest{

    private JqueryUIMenusPage jQueryUIMenusPage;

    @BeforeEach
    void setupPage() {
        jQueryUIMenusPage = new JqueryUIMenusPage(page);
        mainPage.navigateToJqueryUIMenus();
    }

    @Test
    @Tag("smoke")
    void disabledItemDoesNotOpenSubmenu() {
       assertThat(jQueryUIMenusPage.header).isVisible();
       assertThat(jQueryUIMenusPage.disabledItem).hasAttribute("aria-disabled", "true");
       jQueryUIMenusPage.disabledItem.hover();
       assertThat(jQueryUIMenusPage.disabledSubmenu).isHidden();
    }

    @Test
    void downloadPdfFile() {

        assertThat(jQueryUIMenusPage.header).isVisible();
        jQueryUIMenusPage.enabledLink.hover();
        jQueryUIMenusPage.downloadsLink.hover();
        Download download = page.waitForDownload(() -> jQueryUIMenusPage.pdfLink.click());
        Assertions.assertEquals("menu.pdf", download.suggestedFilename());
    }
    @Test
    void downloadCsvFile() {
        assertThat(jQueryUIMenusPage.header).isVisible();
        jQueryUIMenusPage.enabledLink.hover();
        jQueryUIMenusPage.downloadsLink.hover();
        Download download = page.waitForDownload(() -> jQueryUIMenusPage.csvLink.click());
        Assertions.assertEquals("menu.csv", download.suggestedFilename());
    }

    @Test
    void downloadExcelFile() {
        assertThat(jQueryUIMenusPage.header).isVisible();
        jQueryUIMenusPage.enabledLink.hover();
        jQueryUIMenusPage.downloadsLink.hover();
        Download download = page.waitForDownload(() -> jQueryUIMenusPage.excelLink.click());
        Assertions.assertEquals("menu.xls", download.suggestedFilename());
    }

    @Test
    void canReturnToJQueryUIAndOpenMenuAgain() {
        assertThat(jQueryUIMenusPage.header).hasText("JQueryUI - Menu");
        jQueryUIMenusPage.enabledLink.hover();
        jQueryUIMenusPage.backToJQueryUILink.click();
        assertThat(jQueryUIMenusPage.jqueryUIHeading).hasText("JQuery UI");
        jQueryUIMenusPage.menuLink.click();
        assertThat(jQueryUIMenusPage.header).hasText("JQueryUI - Menu");
    }


}
