package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class EntryAdPage extends BasePage{
    public EntryAdPage(Page page){
        super(page);
    }

    public Locator header = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Entry Ad").setExact(true));
    public Locator modal = page.locator("#modal");
    public Locator modalHeader = modal.getByRole(AriaRole.HEADING, new Locator.GetByRoleOptions().setName("This is a modal window").setExact(true));
    public Locator modalBody = modal.locator(".modal-body");
    public Locator closeLink = modal.getByText("Close", new Locator.GetByTextOptions().setExact(true));
    public Locator modalBackground = modal.locator(".underlay");
    public Locator restartAdLink = page.locator("#restart-ad");
    public Locator footer = page.locator("#page-footer");

    public void closeModal() {
        page.waitForResponse(
                response -> response.url().endsWith("/entry_ad")
                        && response.request().method().equals("POST"),
                () -> closeLink.click()
        );
    }
}
