package tests;

import com.microsoft.playwright.Dialog;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.BasePage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class JavaScriptAlertsTest extends BaseTest{
    @Test
    void weCanNavigateToJavaScriptAlertsPage() {
        mainPage.javaScriptAlertsLink.click();
        assertThat(javaScriptAlertsPage.header).hasText("JavaScript Alerts");
        assertThat(javaScriptAlertsPage.alertButton).isVisible();
        assertThat(javaScriptAlertsPage.confirmButton).isVisible();
        assertThat(javaScriptAlertsPage.promptButton).isVisible();
        assertThat(javaScriptAlertsPage.result).hasText("");
    }
    @Test
    void acceptsJavaScriptAlert() {
        mainPage.javaScriptAlertsLink.click();
        page.onDialog(dialog -> {Assertions.assertEquals("alert", dialog.type());
            Assertions.assertEquals("I am a JS Alert", dialog.message());
            dialog.accept();
        });
        javaScriptAlertsPage.alertButton.click();
        assertThat(javaScriptAlertsPage.result).hasText("You successfully clicked an alert");
    }

    @Test
    void acceptsJavaScriptConfirm() {
        mainPage.javaScriptAlertsLink.click();
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
        mainPage.javaScriptAlertsLink.click();
        page.onDialog(Dialog::dismiss);
        javaScriptAlertsPage.confirmButton.click();
        assertThat(javaScriptAlertsPage.result).hasText("You clicked: Cancel");
    }
    @Test
    void entersTextInJavaScriptPrompt() {
        mainPage.javaScriptAlertsLink.click();
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
        mainPage.javaScriptAlertsLink.click();
        page.onDialog(dialog -> {
            Assertions.assertEquals("prompt", dialog.type());
            Assertions.assertEquals("I am a JS prompt", dialog.message());
            dialog.dismiss();
        });
        javaScriptAlertsPage.promptButton.click();
        assertThat(javaScriptAlertsPage.result).hasText("You entered: null");
    }
}
