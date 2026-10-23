package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class GetByRolePage extends BasePage{

    public Locator goodLocator=page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("A/B Testing"));

    //Regex
    public Locator regexGetByRole= page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("A/B.*"));


    public GetByRolePage(Page page){
        super(page);
    }

}
