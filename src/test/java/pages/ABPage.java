package pages;


import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class ABPage extends BasePage {

    public ABPage(Page page){
        super(page);


    }
    public Locator header = page.locator("#content .example > h3");
    public Locator text = page.locator("#content .example > p");
}
