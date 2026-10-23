package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.Locator;


public class AddRemovePage extends BasePage{

    public Locator addRemoveLink;

    public AddRemovePage(Page page){
        super(page);

    }
    public Locator AddElementBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add Element"));
    public Locator DeleteButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Delete"));
}
