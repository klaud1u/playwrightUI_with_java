package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class BasePage {

    protected Page page;
    public BasePage(Page page){

        this.page=page;
    }

    public Page openNewTab(Locator link) {
        return page.waitForPopup(link::click);
    }
}
