package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class DynamicControlsPage extends BasePage{
    public DynamicControlsPage(Page page){
        super(page);
    }

    public Locator header = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Dynamic Controls").setExact(true));
    public Locator checkbox = page.locator("#checkbox-example").getByRole(AriaRole.CHECKBOX);
    public Locator removeBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Remove").setExact(true));
    public Locator addBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add").setExact(true));
    public Locator checkboxLoading = page.locator("#checkbox-example #loading");
    public Locator checkboxMessage = page.locator("#checkbox-example #message");
    public Locator inputField = page.locator("#input-example input");
    public Locator enableBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Enable").setExact(true));
    public Locator disableBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Disable").setExact(true));
    public Locator inputLoading = page.locator("#input-example #loading");
    public Locator inputMessage = page.locator("#input-example #message");
}

