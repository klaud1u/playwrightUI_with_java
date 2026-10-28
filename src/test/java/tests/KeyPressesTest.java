package tests;

import config.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.KeyPressesPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
@Tag("regression")
public class KeyPressesTest extends BaseTest{

    private KeyPressesPage keyPressesPage;

    @BeforeEach
    void setupPage() {
        keyPressesPage = new KeyPressesPage(page);
        mainPage.navigateToKeyPresses();
    }

    @Test
    @Tag("smoke")
    void pageDisplaysExpectedContent() {
        assertThat(page).hasURL(TestConfig.BASE_URL + "/key_presses");
        assertThat(keyPressesPage.header).hasText("Key Presses");
        assertThat(keyPressesPage.input).isVisible();
        assertThat(keyPressesPage.input).isEditable();
        assertThat(keyPressesPage.input).hasValue("");
        assertThat(keyPressesPage.result).isHidden();
    }

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
        assertThat(keyPressesPage.header).hasText("Key Presses");
        assertThat(keyPressesPage.result).isHidden();
        page.keyboard().press(key);
        assertThat(keyPressesPage.result).hasText("You entered: " + expectedKey);
        assertThat(keyPressesPage.input).hasValue("");
    }

    @Test
    void pressingEnterAfterTypingClearsResultMessage() {
        keyPressesPage.input.click();
        keyPressesPage.input.press("a");
        assertThat(keyPressesPage.result).hasText("You entered: A");
        keyPressesPage.input.press("Enter");
        assertThat(keyPressesPage.result).isHidden();
    }

}
