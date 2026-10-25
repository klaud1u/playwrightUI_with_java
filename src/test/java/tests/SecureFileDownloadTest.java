package tests;


import com.microsoft.playwright.Download;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.SecureFileDownloadPage;


import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class SecureFileDownloadTest extends BaseTest{
    private SecureFileDownloadPage secureFileDownloadPage;



    @BeforeEach
    public void setupPage()
    {
        secureFileDownloadPage =new SecureFileDownloadPage(page);
        mainPage.secureFileDownloadLink.click();
    }

    @Test
    void weCanNavigateToSecureFileDownloadPage() {
        assertThat(secureFileDownloadPage.header).hasText("Secure File Downloader");
        assertThat(secureFileDownloadPage.firstFile).isVisible();
    }

    @Test
    void downloadsFirstFileWithValidCredentials() {
        assertThat(secureFileDownloadPage.header).isVisible();
        String expectedFileName = secureFileDownloadPage.firstFile.textContent().trim();
        Download download = page.waitForDownload(() -> secureFileDownloadPage.firstFile.click());
        Assertions.assertEquals(expectedFileName, download.suggestedFilename());
    }

}
