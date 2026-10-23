package tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class InputsTest extends BaseTest {

    @Test
    void numberInputIsInitiallyEmpty() {
        mainPage.inputsLink.click();
        assertThat(inputsPage.header).isVisible();
        assertThat(inputsPage.numberInput).hasValue("");
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "233", "-3"})
    void numberInputAcceptsValues(String value) {
        mainPage.inputsLink.click();
        inputsPage.numberInput.fill(value);
        assertThat(inputsPage.numberInput).hasValue(value);
    }

    @ParameterizedTest
    @CsvSource({
            "abc, ''",
            "a1, 1",
            "a1b2, 12"
    })
    void numberInputIgnoresLetters(String typedText, String expectedValue) {
        mainPage.inputsLink.click();
        inputsPage.numberInput.fill("");
        inputsPage.numberInput.click();
        inputsPage.numberInput.pressSequentially(typedText);
        assertThat(inputsPage.numberInput).hasValue(expectedValue);
    }

    @Test
    void arrowUpIncrementsNumber() {
        mainPage.inputsLink.click();
        inputsPage.numberInput.fill("5");
        inputsPage.numberInput.press("ArrowUp");
        assertThat(inputsPage.numberInput).hasValue("6");
    }
    @Test
    void arrowDownIncrementsNumber() {
        mainPage.inputsLink.click();
        inputsPage.numberInput.fill("-1");
        inputsPage.numberInput.press("ArrowDown");
        assertThat(inputsPage.numberInput).hasValue("-2");
    }
    @Test
    void clickingArrowUpIncrementsNumber() {
        mainPage.inputsLink.click();
        inputsPage.numberInput.fill("0");
        inputsPage.clickArrowUp();
        assertThat(inputsPage.numberInput).hasValue("1");
    }

    @Test
    void clickingArrowDownDecrementsNumber() {
        mainPage.inputsLink.click();
        inputsPage.numberInput.fill("0");
        inputsPage.clickArrowDown();
        assertThat(inputsPage.numberInput).hasValue("-1");
    }
    @Test
    void clickingArrowsIncrementsAndDecrementsNumber() {
        mainPage.inputsLink.click();
        inputsPage.numberInput.fill("0");
        inputsPage.clickArrowUp();
        assertThat(inputsPage.numberInput).hasValue("1");
        inputsPage.clickArrowDown();
        assertThat(inputsPage.numberInput).hasValue("0");
        inputsPage.clickArrowDown();
        assertThat(inputsPage.numberInput).hasValue("-1");
    }
}
