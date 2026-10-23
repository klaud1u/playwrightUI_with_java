package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class FileDownloadPage extends BasePage{

    public FileDownloadPage(Page page){
        super(page);
    }
    public Locator header = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("File Downloader").setExact(true));
    public Locator testFile = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("hello.txt").setExact(true));
    public Locator firstFile = page.locator(".example a").first();
}
