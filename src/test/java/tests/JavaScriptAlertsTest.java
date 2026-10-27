package tests;

import com.microsoft.playwright.Dialog;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.BasePage;
import pages.JavaScriptAlertsPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class JavaScriptAlertsTest extends BaseTest{
    private JavaScriptAlertsPage javaScriptAlertsPage;

    @BeforeEach
    void setupPage() {
        javaScriptAlertsPage = new JavaScriptAlertsPage(page);
        mainPage.javaScriptAlertsLink.click();
    }

    @Test
    void weCanNavigateToJavaScriptAlertsPage() {

        assertThat(javaScriptAlertsPage.header).hasText("JavaScript Alerts");
        assertThat(javaScriptAlertsPage.alertButton).isVisible();
        assertThat(javaScriptAlertsPage.confirmButton).isVisible();
        assertThat(javaScriptAlertsPage.promptButton).isVisible();
        assertThat(javaScriptAlertsPage.result).hasText("");
    }
    @Test
    void acceptsJavaScriptAlert() {
        page.onDialog(dialog -> {Assertions.assertEquals("alert", dialog.type());
            Assertions.assertEquals("I am a JS Alert", dialog.message());
            dialog.accept();
        });
        javaScriptAlertsPage.alertButton.click();
        assertThat(javaScriptAlertsPage.result).hasText("You successfully clicked an alert");
    }

    @Test
    void acceptsJavaScriptConfirm() {
        page.onDialog(dialog -> {
            Assertions.assertEquals("confirm", dialog.type());
            Assertions.assertEquals("I am a JS Confirm", dialog.message());
            dialog.accept();
        });
        javaScriptAlertsPage.confirmButton.click();
        assertThat(javaScriptAlertsPage.result).hasText("You clicked: Ok");
    }
    @Test
    void dismissesJavaScriptConfirm() {
        page.onDialog(Dialog::dismiss);
        javaScriptAlertsPage.confirmButton.click();
        assertThat(javaScriptAlertsPage.result).hasText("You clicked: Cancel");
    }
    @Test
    void entersTextInJavaScriptPrompt() {
        page.onDialog(dialog -> {
            Assertions.assertEquals("prompt", dialog.type());
            Assertions.assertEquals("I am a JS prompt", dialog.message());
            dialog.accept("Claudiu");
        });
        javaScriptAlertsPage.promptButton.click();
        assertThat(javaScriptAlertsPage.result).hasText("You entered: Claudiu");
    }
    @Test
    void cancelsJavaScriptPrompt() {
        page.onDialog(dialog -> {
            Assertions.assertEquals("prompt", dialog.type());
            Assertions.assertEquals("I am a JS prompt", dialog.message());
            dialog.dismiss();
        });
        javaScriptAlertsPage.promptButton.click();
        assertThat(javaScriptAlertsPage.result).hasText("You entered: null");
    }
}
