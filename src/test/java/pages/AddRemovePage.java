package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.Locator;


public class AddRemovePage extends BasePage{

    public Locator addRemoveLink;

    public AddRemovePage(Page page){
        super(page);

    }
    public Locator addElementBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add Element"));
    public Locator deleteBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Delete"));
    public Locator header = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Add/Remove Elements").setExact(true));

    public void addElement() {
        addElementBtn.click();
    }

    public void addElements(int count) {
        for (int i = 0; i < count; i++) {
            addElement();
        }
    }

    public void deleteElement() {
        deleteBtn.click();
    }

    public void deleteFirstElement() {
        deleteBtn.first().click();
    }

    public void deleteElements(int count) {
        for (int i = 0; i < count; i++) {
            deleteFirstElement();
        }
    }
}
