package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class ShadowDOMPage extends BasePage {
    public ShadowDOMPage(Page page) {
        super(page);
    }

    public Locator header = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Simple template").setExact(true));
    public Locator firstText = page.locator("my-paragraph span");
    public Locator listItems = page.locator("my-paragraph li");
    public Locator firstParagraph = page.locator("my-paragraph p").first();
    public Locator secondParagraph = page.locator("my-paragraph p").nth(1);
}

