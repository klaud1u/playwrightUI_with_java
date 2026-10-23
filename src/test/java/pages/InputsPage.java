package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class InputsPage extends BasePage {
    public InputsPage(Page page) {
        super(page);
    }

    public Locator header = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Inputs").setExact(true));
    public Locator numberInput = page.locator("input[type='number']");


    public void clickArrowUp() {
        var box = numberInput.boundingBox();
        numberInput.click(new Locator.ClickOptions().setPosition(box.width - 12, box.height * 0.3));
    }

    public void clickArrowDown() {
        var box = numberInput.boundingBox();
        numberInput.click(new Locator.ClickOptions().setPosition(box.width - 12, box.height * 0.7));
    }
}
