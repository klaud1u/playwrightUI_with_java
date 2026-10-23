package tests;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import pages.*;

public class BaseTest {
    protected Playwright playwright;
    protected BrowserContext context;
    protected Page page;
    protected MainPage mainPage;
    protected ChallengingDOMPage challengingDOMPage;
    protected ABPage abPage;
    protected BasicAuthPage  basicAuthPage;
    protected BrokenImagesPage brokenImagesPage;
    protected AddRemovePage addRemovePage;
    protected CheckboxesPage checkboxesPage;
    protected DigestAuthenticationPage digestAuthenticationPage;
    protected ContextMenuPage contextMenuPage;
    protected DisappearingElementsPage disappearingElementsPage;
    protected DragAndDropPage dragAndDropPage;
    protected DropdownPage dropdownPage;
    protected DynamicContentPage dynamicContentPage;
    protected FileDownloadPage fileDownloadPage;
    protected FileUploadPage fileUploadPage;
    protected FloatingMenuPage floatingMenuPage;
    protected ForgotPasswordPage forgotPasswordPage;
    protected FormAuthenticationPage formAuthenticationPage;
    protected HorizontalSliderPage horizontalSliderPage;
    protected HoversPage hoversPage;
    protected InfiniteScrollPage infiniteScrollPage;
    protected InputsPage inputsPage;
    protected JqueryUIMenusPage jQueryUIMenusPage;
    protected JavaScriptAlertsPage javaScriptAlertsPage;
    protected KeyPressesPage keyPressesPage;
    protected LargeAndDeepDOMPage largeAndDeepDOMPage;

    @BeforeEach
    void setup(){
        playwright= Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
        context = browser.newContext(new Browser.NewContextOptions().setHttpCredentials("admin", "admin"));
        page=context.newPage();
        page.navigate("https://the-internet.herokuapp.com/");

        mainPage  = new MainPage(page);
        challengingDOMPage = new ChallengingDOMPage(page);
        abPage = new ABPage(page);
        basicAuthPage=new BasicAuthPage(page);
        brokenImagesPage= new BrokenImagesPage(page);
        addRemovePage = new AddRemovePage(page);
        checkboxesPage = new CheckboxesPage(page);
        digestAuthenticationPage = new DigestAuthenticationPage(page);
        contextMenuPage = new ContextMenuPage(page);
        disappearingElementsPage = new DisappearingElementsPage(page);
        dragAndDropPage = new DragAndDropPage(page);
        dropdownPage = new DropdownPage(page);
        dynamicContentPage = new DynamicContentPage(page);
        fileDownloadPage = new FileDownloadPage(page);
        fileUploadPage = new FileUploadPage(page);
        floatingMenuPage = new FloatingMenuPage(page);
        forgotPasswordPage = new ForgotPasswordPage(page);
        formAuthenticationPage = new FormAuthenticationPage(page);
        horizontalSliderPage = new HorizontalSliderPage(page);
        hoversPage = new HoversPage(page);
        infiniteScrollPage = new InfiniteScrollPage(page);
        inputsPage = new InputsPage(page);
        jQueryUIMenusPage = new JqueryUIMenusPage(page);
        javaScriptAlertsPage = new JavaScriptAlertsPage(page);
        keyPressesPage = new KeyPressesPage(page);
        largeAndDeepDOMPage = new LargeAndDeepDOMPage(page);
    }

    @AfterEach
    void teardown() throws InterruptedException {
        Thread.sleep(2000);
        context.close();

    }
}
