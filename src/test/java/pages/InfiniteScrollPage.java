package pages;


import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class InfiniteScrollPage extends BasePage {
    public InfiniteScrollPage(Page page) {
        super(page);
    }
    public Locator header = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Infinite Scroll").setExact(true));
    public Locator textParagraph = page.locator(".scroll .jscroll-added");
    public Locator loadingScroll=page.locator(".jscroll-loading");


    public void scrollToBottom(int times) {
        for (int i = 0; i < times; i++) {
           page.waitForCondition(() -> loadingScroll.count() == 0);
            int paragraphsBefore = textParagraph.count();
            page.evaluate("window.scrollTo(0, document.body.scrollHeight)");
            page.waitForCondition(() -> textParagraph.count() > paragraphsBefore && loadingScroll.count() == 0);
        }
    }

}
