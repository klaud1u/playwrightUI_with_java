package tests;

import com.microsoft.playwright.*;
import io.qameta.allure.Allure;
import io.qameta.allure.AttachmentOptions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import pages.*;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.io.ByteArrayInputStream;
import java.util.UUID;

public class BaseTest {
    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;
    protected MainPage mainPage;
    protected ChallengingDOMPage challengingDOMPage;
    protected ABPage abPage;
    protected BasicAuthPage basicAuthPage;
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
    protected LargeFormPage largeFormPage;

    private boolean testFailed;

    @RegisterExtension
    protected final AfterTestExecutionCallback failureEvidence = testContext -> {
        testFailed = testContext.getExecutionException().isPresent();

        if (!testFailed) {
            return;
        }

        Path folder = Path.of("target", "test-artifacts", testContext.getRequiredTestMethod().getName() + "-" + UUID.randomUUID());

        Files.createDirectories(folder);

        try {
            if (page != null && !page.isClosed()) {
                byte[] screenshot = page.screenshot(new Page.ScreenshotOptions().setPath(folder.resolve("screenshot.png")).setFullPage(true));

                Allure.attachment("Screenshot at failure", "image/png", new ByteArrayInputStream(screenshot), AttachmentOptions.withFileExtension(".png"));
            }
        } catch (Exception error) {
            System.err.println("Could not save screenshot: " + error.getMessage());
        }

        try {
            if (context != null) {
                Path trace = folder.resolve("trace.zip");
                context.tracing().stop(new Tracing.StopOptions().setPath(trace));
                try (InputStream stream = Files.newInputStream(trace)) {
                    Allure.attachment("Playwright trace", "application/zip", stream, AttachmentOptions.withFileExtension(".zip"));
                }
            }
        } catch (Exception error) {
            System.err.println("Could not save trace: " + error.getMessage());
        }
    };

    @BeforeEach
    void setup() {
        testFailed = false;

        playwright = Playwright.create();
        boolean headless = Boolean.parseBoolean(
                System.getenv().getOrDefault("HEADLESS", "true")
        );
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(headless)
                        .setArgs(java.util.List.of("--start-maximized"))
        );

        //browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless));
        //browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
        context = browser.newContext(
                new Browser.NewContextOptions()
                        .setHttpCredentials("admin", "admin")
                        .setViewportSize(1920, 1080)
                        .setRecordVideoDir(Path.of("target/videos"))
                        .setRecordVideoSize(1920, 1080)
        );
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true));

        page = context.newPage();
        page.navigate("https://the-internet.herokuapp.com/");

        mainPage = new MainPage(page);
        challengingDOMPage = new ChallengingDOMPage(page);
        abPage = new ABPage(page);
        basicAuthPage = new BasicAuthPage(page);
        brokenImagesPage = new BrokenImagesPage(page);
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
        largeFormPage = new LargeFormPage(page);
    }

    @AfterEach
    void teardown() {
        Video video = page != null ? page.video() : null;

        try {
            if (context != null) {
                context.close();
            }

            if (video != null) {
                if (testFailed) {
                    try (InputStream stream = Files.newInputStream(video.path())) {
                        Allure.attachment(
                                "Test video",
                                "video/webm",
                                stream,
                                AttachmentOptions.withFileExtension(".webm")
                        );
                    }
                } else {
                    video.delete();
                }
            }
        } catch (Exception error) {
            System.err.println("Could not process video: " + error.getMessage());
        } finally {
            try {
                if (browser != null) {
                    browser.close();
                }
            } finally {
                if (playwright != null) {
                    playwright.close();
                }
            }
        }
    }
}