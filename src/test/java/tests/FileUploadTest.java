package tests;

import com.microsoft.playwright.FileChooser;
import com.microsoft.playwright.Locator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class FileUploadTest extends BaseTest{
    private Path helloFile() {return Paths.get(System.getProperty("user.home"), "Downloads", "hello.txt");
    }


    @Test
    void navigateToFileUpload() {
        mainPage.fileUploadLink.click();
        assertThat(fileUploadPage.header).isVisible();
    }

    @Test
    void canUploadHelloTxtFile() {
        mainPage.fileUploadLink.click();
        Path file = helloFile();
        Assertions.assertTrue(file.toFile().exists());
        fileUploadPage.chooseFileBtn.setInputFiles(file);
        fileUploadPage.uploadBtn.click();
        assertThat(fileUploadPage.uploadedHeader).isVisible();
        assertThat(fileUploadPage.uploadedFileName).hasText("hello.txt");
    }

    @Test
    void uploadingWithoutFile() {
        mainPage.fileUploadLink.click();
        fileUploadPage.uploadBtn.click();
        assertThat(fileUploadPage.uploadErrorHeader).isVisible();
        assertThat(fileUploadPage.uploadErrorMessage).hasText("No file was chosen. Please choose a file to upload.");
        assertThat(fileUploadPage.backToUploaderLink).isVisible();
    }

    @Test
    void backLinkReturnsToFileUploader() {
        mainPage.fileUploadLink.click();
        fileUploadPage.uploadBtn.click();
        fileUploadPage.backToUploaderLink.click();
        assertThat(fileUploadPage.header).isVisible();
        assertThat(fileUploadPage.chooseFileBtn).isVisible();
        assertThat(fileUploadPage.uploadBtn).isVisible();
    }




}

