package tests;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.ViewportSize;
import config.TestConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import pages.*;

import java.nio.file.Path;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.RegisterExtension;
import testdata.HttpAuthTestData;
import utils.TestEvidence;


public class BaseTest {
    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;
    protected MainPage mainPage;
    private boolean testFailed;

    @RegisterExtension
    protected final AfterTestExecutionCallback failureEvidence = testContext -> {
        testFailed = testContext.getExecutionException().isPresent();

        if (testFailed) {
            TestEvidence.saveFailureEvidence(
                    page,
                    context,
                    testContext.getRequiredTestMethod().getName()
            );
        }
    };

    @BeforeEach
    void setup() {
        testFailed = false;

        playwright = Playwright.create();
        boolean headless = TestConfig.HEADLESS;
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(headless)
                        .setArgs(java.util.List.of("--start-maximized"))
        );
        context = browser.newContext(
                new Browser.NewContextOptions()
                        .setHttpCredentials(HttpAuthTestData.USERNAME, HttpAuthTestData.PASSWORD)
                        .setViewportSize(headless ? new ViewportSize(1920, 1080) : null)
                        .setRecordVideoDir(Path.of("target/videos"))
                        .setRecordVideoSize(1280, 720)
        );
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true));
        page = context.newPage();
        page.navigate(TestConfig.BASE_URL + "/");

        mainPage = new MainPage(page);

    }

    @AfterEach
    void teardown() {
        Video video = page != null ? page.video() : null;
        try {
            if (context != null) {
                context.close();
            }

            TestEvidence.processVideo(video, testFailed);
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