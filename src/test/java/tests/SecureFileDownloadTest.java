package tests;


import com.microsoft.playwright.Download;
import config.TestConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.SecureFileDownloadPage;


import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
@Tag("regression")
public class SecureFileDownloadTest extends BaseTest{
    private SecureFileDownloadPage secureFileDownloadPage;



    @BeforeEach
    public void setupPage()
    {
        secureFileDownloadPage =new SecureFileDownloadPage(page);
        mainPage.navigateToSecureFileDownload();
    }

    @Test
    @Tag("smoke")
    void weCanNavigateToSecureFileDownloadPage() {
        assertThat(page).hasURL(TestConfig.BASE_URL + "/download_secure");
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
