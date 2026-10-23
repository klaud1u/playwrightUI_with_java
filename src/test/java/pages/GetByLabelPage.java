package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class GetByLabelPage extends BasePage{


    public GetByLabelPage(Page page){
        super(page);
    }

    public Locator userName=page.getByLabel("username");
    public Locator placeHolder=page.getByLabel("placeHolder");
    public Locator getByAltText=page.getByLabel("getByAltText");
    public Locator getByTestId=page.getByTestId("getByTitle");
    public Locator GetByTitle=page.getByTitle("getByTitle");
}
