package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class ReadOnlyInputsPage extends BasePage {
    public ReadOnlyInputsPage(Page page) {
        super(page);
    }
    public Locator header = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Read-only Inputs").setExact(true));
    public Locator readOnlyCheckbox = page.getByLabel("Text field is read-only");
    public Locator textField = page.locator("#textfield");
    public Locator alwaysReadOnlyField = page.getByLabel("Always read-only");
    public Locator disabledField = page.locator("#disabled");
}

