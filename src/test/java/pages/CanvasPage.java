package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class CanvasPage extends BasePage {
    public CanvasPage(Page page){
        super(page);

    }

    public Locator header = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Canvas").setExact(true));
    public Locator canvas = page.locator("#canvas");
    public Locator submittedMsg = page.locator("#canvas-log");

}
