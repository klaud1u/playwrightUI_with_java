package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class DragAndDropPage extends BasePage{

    public DragAndDropPage(Page page) {
        super(page);
    }
    public Locator header = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Drag and Drop"));
    public Locator columnA = page.locator("#column-a");
    public Locator columnB = page.locator("#column-b");

}
