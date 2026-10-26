package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class ExitIntentPage extends BasePage {
    public ExitIntentPage(Page page) {
        super(page);
    }
    public Locator header = page.getByRole(
            AriaRole.HEADING,
            new Page.GetByRoleOptions().setName("Exit Intent").setExact(true)
    );

    public Locator modal = page.locator("#ouibounce-modal");
    public Locator modalHeader = modal.getByRole(AriaRole.HEADING, new Locator.GetByRoleOptions().setName("This is a modal window").setExact(true));
    public Locator modalBody = modal.locator(".modal-body");
    public Locator closeLink = modal.getByText("Close", new Locator.GetByTextOptions().setExact(true));
    public Locator modalBackground = modal.locator(".underlay");
    public Locator footer = page.locator("#page-footer");

    public void moveMouseOutsidePage() {
        page.mouse().move(200, 200);
        page.mouse().move(200, -10);
    }


}
