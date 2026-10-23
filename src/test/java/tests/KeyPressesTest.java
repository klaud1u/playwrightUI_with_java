package tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class KeyPressesTest extends BaseTest{

    @ParameterizedTest
    @CsvSource({
            "a, A",
            "5, 5",
            "!, 1",
            "., PERIOD",
            "Tab, TAB",
            "Escape, ESCAPE"
    })
    void pressedKeyIsShownWithoutTypingInInput(String key, String expectedKey) {
        mainPage.keyPressesLink.click();
        assertThat(keyPressesPage.header).hasText("Key Presses");
        assertThat(keyPressesPage.result).isHidden();
        page.keyboard().press(key);
        assertThat(keyPressesPage.result).hasText("You entered: " + expectedKey);
        assertThat(keyPressesPage.input).hasValue("");
    }

    @Test
    void pressingEnterAfterTypingClearsResultMessage() {
        mainPage.keyPressesLink.click();
        keyPressesPage.input.click();
        keyPressesPage.input.press("a");
        assertThat(keyPressesPage.result).hasText("You entered: A");
        keyPressesPage.input.press("Enter");
        assertThat(keyPressesPage.result).isHidden();
    }

}
