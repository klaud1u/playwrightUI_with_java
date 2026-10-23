package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class BasicAuthPage extends BasePage{

  public BasicAuthPage(Page page){
      super(page);

  }
  public Locator basicAuthHeader = page.getByText("Basic Auth");

}
