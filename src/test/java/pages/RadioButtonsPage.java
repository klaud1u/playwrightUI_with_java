package pages;


import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class RadioButtonsPage extends BasePage {
    public RadioButtonsPage(Page page) {
        super(page);
    }

    public Locator header = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Radio Buttons").setExact(true));
    public Locator red = page.getByLabel("Red", new Page.GetByLabelOptions().setExact(true));
    public Locator blue = page.getByLabel("Blue", new Page.GetByLabelOptions().setExact(true));
    public Locator green = page.getByLabel("Green", new Page.GetByLabelOptions().setExact(true));
    public Locator football = page.locator("input[name='sport'][value='football']");
    public Locator tennis = page.locator("input[name='sport'][value='tennis']");
    public Locator hockey = page.locator("input[name='sport'][value='hockey']");
    public Locator result = page.locator("#result");

    public Locator colorOption(String color) {
        return page.locator("input[name='color'][value='" + color + "']");
    }

    public Locator sportOption(String sport) {
        return page.locator("input[name='sport'][value='" + sport + "']");
    }
}
