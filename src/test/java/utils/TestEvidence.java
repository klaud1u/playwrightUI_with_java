package utils;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Tracing;
import com.microsoft.playwright.Video;
import io.qameta.allure.Allure;
import io.qameta.allure.AttachmentOptions;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

public final class TestEvidence {

    private TestEvidence() {}

    public static void saveFailureEvidence(
            Page page,
            BrowserContext context,
            String testName
    ) {
        Path folder = Path.of(
                "target",
                "test-artifacts",
                testName + "-" + UUID.randomUUID()
        );

        try {
            Files.createDirectories(folder);
        } catch (Exception error) {
            System.err.println(
                    "Could not create evidence folder: " + error.getMessage()
            );
            return;
        }

        saveScreenshot(page, folder);
        saveTrace(context, folder);
    }

    private static void saveScreenshot(Page page, Path folder) {
        try {
            if (page == null || page.isClosed()) {
                return;
            }

            byte[] screenshot = page.screenshot(
                    new Page.ScreenshotOptions()
                            .setPath(folder.resolve("screenshot.png"))
                            .setFullPage(true)
            );

            Allure.attachment(
                    "Screenshot at failure",
                    "image/png",
                    new ByteArrayInputStream(screenshot),
                    AttachmentOptions.withFileExtension(".png")
            );
        } catch (Exception error) {
            System.err.println(
                    "Could not save screenshot: " + error.getMessage()
            );
        }
    }

    private static void saveTrace(BrowserContext context, Path folder) {
        try {
            if (context == null) {
                return;
            }

            Path trace = folder.resolve("trace.zip");

            context.tracing().stop(
                    new Tracing.StopOptions().setPath(trace)
            );

            try (InputStream stream = Files.newInputStream(trace)) {
                Allure.attachment(
                        "Playwright trace",
                        "application/zip",
                        stream,
                        AttachmentOptions.withFileExtension(".zip")
                );
            }
        } catch (Exception error) {
            System.err.println(
                    "Could not save trace: " + error.getMessage()
            );
        }
    }

    // Se apelează după închiderea contextului.
    public static void processVideo(Video video, boolean testFailed) {
        try {
            if (video == null) {
                return;
            }

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
        } catch (Exception error) {
            System.err.println(
                    "Could not process video: " + error.getMessage()
            );
        }
    }
}