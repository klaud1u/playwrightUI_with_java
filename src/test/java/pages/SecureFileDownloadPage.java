package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class SecureFileDownloadPage extends BasePage {
    public SecureFileDownloadPage(Page page) {
        super(page);
    }

    public Locator header = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Secure File Downloader").setExact(true));
    public Locator firstFile = page.locator(".example a").first();
}

