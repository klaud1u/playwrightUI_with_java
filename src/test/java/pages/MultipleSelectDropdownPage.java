package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class MultipleSelectDropdownPage extends BasePage{
    public MultipleSelectDropdownPage(Page page) {
        super(page);

    }

    public Locator header = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Multiple Select Dropdown List").setExact(true));
    public Locator optionsList = page.locator("#multiple-dropdown");
    public Locator options = optionsList.locator("option");
    public Locator option1 = optionsList.getByRole(AriaRole.OPTION, new Locator.GetByRoleOptions().setName("Option 1").setExact(true));
    public Locator option2 = optionsList.getByRole(AriaRole.OPTION, new Locator.GetByRoleOptions().setName("Option 2").setExact(true));
    public Locator option3 = optionsList.getByRole(AriaRole.OPTION, new Locator.GetByRoleOptions().setName("Option 3").setExact(true));
    public Locator option4 = optionsList.getByRole(AriaRole.OPTION, new Locator.GetByRoleOptions().setName("Option 4").setExact(true));
}
