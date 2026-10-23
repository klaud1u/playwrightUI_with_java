package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LocatorPage extends BasePage {
    public LocatorPage(Page page) {
        super(page);
    }

    public Locator idLocator=page.locator("#content");


    public Locator locatorTag=page.locator("a");
    public Locator locatorClass =page.locator(".large-12 columns");
    public Locator locatorAttribute =page.locator("[href='/abtest']");

    public Locator locatorText =page.locator("text=A/B Testing");

    public Locator tag_text=page.locator("a:has-text('A/B Testing')");
    public Locator exactText=page.locator("a:text-is('A/B Testing')");


    //text regex
    public Locator regexLoc=page.locator("text-matches(A/B.*)");
    //nth-child
    public Locator nthChild=page.locator("a:nth-child(2)");
    //direct child
    public Locator directChild=page.locator("div>a");

    //nth-type
    public Locator nthtype=page.locator("a:nth-of-type(2)");

    //first
    public Locator firtElement=page.locator("li").first();

    //last
    public Locator lastElement=page.locator("li").last();
}
