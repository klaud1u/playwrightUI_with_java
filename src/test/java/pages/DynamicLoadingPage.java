package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class DynamicLoadingPage extends BasePage{
    public DynamicLoadingPage(Page page){
        super(page);
    }

    public Locator header = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Dynamically Loaded Page Elements").setExact(true));
    public Locator exampleOneLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Example 1: Element on page that is hidden").setExact(true));
    public Locator exampleTwoLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Example 2: Element rendered after the fact").setExact(true));
    public Locator exampleHeading = page.locator(".example > h4");
    public Locator startBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Start").setExact(true));
    public Locator loadingIndicator = page.locator("#loading");
    public Locator helloText = page.locator("#finish h4");

    public Locator exampleLink(int exampleNumber) {
        return page.locator("a[href='/dynamic_loading/" + exampleNumber + "']");
    }
}
