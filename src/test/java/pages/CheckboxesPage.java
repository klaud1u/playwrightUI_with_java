package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class CheckboxesPage extends BasePage {


    public CheckboxesPage(Page page) {
        super(page);
    }

    public Locator header = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Checkboxes"));
    public Locator firstCheckbox = page.getByRole(AriaRole.CHECKBOX).nth(0);
    public Locator secondCheckbox = page.getByRole(AriaRole.CHECKBOX).nth(1);

}
