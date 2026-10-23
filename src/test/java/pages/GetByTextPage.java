package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import tests.BaseTest;

import java.util.regex.Pattern;

public class GetByTextPage extends BaseTest {
    public GetByTextPage(Page page) {
        super();
    }

    public Locator getByText = page.getByText("A/B Testing");

    public Locator notExactMatch=page.getByText("Testing",new Page.GetByTextOptions().setExact(false));
    public Locator regex=page.getByText(Pattern.compile("^A/B Testing$"));
}