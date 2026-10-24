package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class MainPage extends BasePage {
    public MainPage(Page page) {
        super(page);
    }

    public Locator AbTestingLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("A/B Testing"));
    public Locator addRemoveElements = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Add/Remove Elements"));
    public Locator brokenImagesLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Broken Images"));
    public Locator basicAuthLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Basic Auth"));
    public Locator challengingDOMLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Challenging DOM"));
    public Locator checkboxLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Checkboxes"));
    public Locator digestAuthenticationLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Digest Authentication"));
    public Locator contextMenuLink= page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Context Menu"));
    public Locator disappearingElementsLink= page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Disappearing Elements"));
    public Locator dragAndDropLink = page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Drag and Drop"));
    public Locator dropdownLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Dropdown").setExact(true));
    public Locator dynamicContentLink = page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Dynamic Content"));
    public Locator fileDownloadLink = page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("File Download").setExact(true));
    public Locator fileUploadLink = page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("File Upload").setExact(true));
    public Locator floatingMenuLink = page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Floating Menu").setExact(true));
    public Locator forgotPasswordLink =page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Forgot Password").setExact(true));
    public Locator formAuthenticationLink =page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Form Authentication").setExact(true));
    public Locator horizontalSliderLink =page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Horizontal Slider").setExact(true));
    public Locator hoversLink =page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Hovers").setExact(true));
    public Locator infiniteScrollLink =page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Infinite Scroll").setExact(true));
    public Locator inputsLink =page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Inputs").setExact(true));
    public Locator jqueryUiMenusLink =page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("JQuery UI Menus").setExact(true));
    public Locator keyPressesLink =page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Key Presses").setExact(true));
    public Locator javaScriptAlertsLink =page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("JavaScript Alerts").setExact(true));
    public Locator largeAndDeepDOMLink =page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Large & Deep DOM").setExact(true));
    public Locator largeFormLink =page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Large Form").setExact(true));

}