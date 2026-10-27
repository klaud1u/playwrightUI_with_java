package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class JavaScriptOnloadEventErrorPage extends BasePage{
    public JavaScriptOnloadEventErrorPage(Page page) {
        super(page);
    }
    public Locator text = page.locator("body p");
}

