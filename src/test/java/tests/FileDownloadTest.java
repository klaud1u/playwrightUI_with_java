package tests;

import com.microsoft.playwright.Download;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.FileDownloadPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class FileDownloadTest extends BaseTest {
    private FileDownloadPage fileDownloadPage;

    @BeforeEach
    void setupPage() {
        fileDownloadPage = new FileDownloadPage(page);
        mainPage.fileDownloadLink.click();
    }

    @Test
    void downloadsFirstFileFromList() {
        assertThat(fileDownloadPage.header).isVisible();
        String expectedFileName = fileDownloadPage.firstFile.textContent().trim();
        Download download = page.waitForDownload(() -> fileDownloadPage.firstFile.click());
        Assertions.assertEquals(expectedFileName, download.suggestedFilename());
    }

}
