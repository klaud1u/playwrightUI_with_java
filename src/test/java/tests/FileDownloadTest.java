package tests;

import com.microsoft.playwright.Download;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class FileDownloadTest extends BaseTest {
    @Test
    void downloadsFirstFileFromList() {
        mainPage.fileDownloadLink.click();
        assertThat(fileDownloadPage.header).isVisible();
        String expectedFileName = fileDownloadPage.firstFile.textContent().trim();
        Download download = page.waitForDownload(() -> fileDownloadPage.firstFile.click());
        Assertions.assertEquals(expectedFileName, download.suggestedFilename());
    }

}
