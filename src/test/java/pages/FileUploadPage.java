package pages;


import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class FileUploadPage extends BasePage {
    public FileUploadPage(Page page) {
        super(page);
    }
    public Locator header = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("File Uploader").setExact(true));
    public Locator chooseFileBtn= page.locator("#file-upload");
    public Locator uploadBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Upload").setExact(true));
    public Locator dropZone = page.locator("#drag-drop-upload");
    public Locator uploadedHeader = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("File Uploaded!").setExact(true));
    public Locator uploadedFileName = page.locator("#uploaded-files");
    public Locator uploadErrorHeader = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Internal Server Error").setExact(true));
    public Locator uploadErrorMessage = page.locator("#upload-error");
    public Locator backToUploaderLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Back to the File Uploader").setExact(true));
}
