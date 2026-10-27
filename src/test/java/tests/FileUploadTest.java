package tests;

import com.microsoft.playwright.FileChooser;
import com.microsoft.playwright.Locator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.FileUploadPage;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class FileUploadTest extends BaseTest{

    private FileUploadPage fileUploadPage;

    @BeforeEach
    void setupPage() {
        fileUploadPage = new FileUploadPage(page);
        mainPage.fileUploadLink.click();
    }

    private Path helloFile() throws Exception {
        return Path.of(Objects.requireNonNull(getClass().getClassLoader().getResource("files/hello.txt")).toURI());
    }


    @Test
    void navigateToFileUpload() {
        assertThat(fileUploadPage.header).isVisible();
    }

    @Test
    void canUploadHelloTxtFile() throws Exception {
        Path file = helloFile();
        Assertions.assertTrue(file.toFile().exists());
        fileUploadPage.chooseFileBtn.setInputFiles(file);
        fileUploadPage.uploadBtn.click();
        assertThat(fileUploadPage.uploadedHeader).isVisible();
        assertThat(fileUploadPage.uploadedFileName).hasText("hello.txt");
    }

    @Test
    void uploadingWithoutFile() {
        fileUploadPage.uploadBtn.click();
        assertThat(fileUploadPage.uploadErrorHeader).isVisible();
        assertThat(fileUploadPage.uploadErrorMessage).hasText("No file was chosen. Please choose a file to upload.");
        assertThat(fileUploadPage.backToUploaderLink).isVisible();
    }

    @Test
    void backLinkReturnsToFileUploader() {
        fileUploadPage.uploadBtn.click();
        fileUploadPage.backToUploaderLink.click();
        assertThat(fileUploadPage.header).isVisible();
        assertThat(fileUploadPage.chooseFileBtn).isVisible();
        assertThat(fileUploadPage.uploadBtn).isVisible();
    }




}

