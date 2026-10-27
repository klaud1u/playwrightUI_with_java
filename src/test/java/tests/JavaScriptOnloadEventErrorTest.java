package tests;

import config.TestConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.JavaScriptOnloadEventErrorPage;

import java.util.ArrayList;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class JavaScriptOnloadEventErrorTest extends BaseTest{

    private JavaScriptOnloadEventErrorPage javaScriptOnloadEventErrorPage;

    @BeforeEach
    void setupPage() {
        javaScriptOnloadEventErrorPage= new JavaScriptOnloadEventErrorPage(page);
        mainPage.javaScriptOnloadEventErrorLink.click();
    }

    @Test
    void weAreOnJavaScriptErrorPage() {
        assertThat(page).hasURL(TestConfig.BASE_URL + "/javascript_error");
        assertThat(javaScriptOnloadEventErrorPage.text).containsText("This page has a JavaScript error in the onload event.");
    }

    @Test
    void loadingPageHaveJavaScriptError() {
        List<String> errors = new ArrayList<>();
        page.onPageError(errors::add);
        page.waitForCondition(() -> !errors.isEmpty());
        Assertions.assertFalse(errors.isEmpty(), "Expected a JavaScript error when loading the page");
    }
}
