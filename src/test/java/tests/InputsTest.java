package tests;

import config.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import pages.InputsPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
@Tag("regression")
public class InputsTest extends BaseTest {
    private InputsPage inputsPage;

    @BeforeEach
    void setupPage() {
        inputsPage = new InputsPage(page);
        mainPage.navigateToInputs();
    }

    @Test
    @Tag("smoke")
    void pageDisplaysExpectedContent() {
        assertThat(page).hasURL(TestConfig.BASE_URL + "/inputs");
        assertThat(inputsPage.header).hasText("Inputs");
        assertThat(inputsPage.numberInput).isVisible();
        assertThat(inputsPage.numberInput).isEditable();
        assertThat(inputsPage.numberInput).hasValue("");
    }

    @Test
    void numberInputIsInitiallyEmpty() {
        assertThat(inputsPage.header).isVisible();
        assertThat(inputsPage.numberInput).hasValue("");
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "233", "-3"})
    void numberInputAcceptsValues(String value) {
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
        inputsPage.numberInput.fill("");
        inputsPage.numberInput.click();
        inputsPage.numberInput.pressSequentially(typedText);
        assertThat(inputsPage.numberInput).hasValue(expectedValue);
    }

    @Test
    void arrowUpIncrementsNumber() {
        inputsPage.numberInput.fill("5");
        inputsPage.numberInput.press("ArrowUp");
        assertThat(inputsPage.numberInput).hasValue("6");
    }
    @Test
    void arrowDownIncrementsNumber() {
        inputsPage.numberInput.fill("-1");
        inputsPage.numberInput.press("ArrowDown");
        assertThat(inputsPage.numberInput).hasValue("-2");
    }
    @Test
    void clickingArrowUpIncrementsNumber() {
        inputsPage.numberInput.fill("0");
        inputsPage.clickArrowUp();
        assertThat(inputsPage.numberInput).hasValue("1");
    }

    @Test
    void clickingArrowDownDecrementsNumber() {
        inputsPage.numberInput.fill("0");
        inputsPage.clickArrowDown();
        assertThat(inputsPage.numberInput).hasValue("-1");
    }
    @Test
    void clickingArrowsIncrementsAndDecrementsNumber() {
        inputsPage.numberInput.fill("0");
        inputsPage.clickArrowUp();
        assertThat(inputsPage.numberInput).hasValue("1");
        inputsPage.clickArrowDown();
        assertThat(inputsPage.numberInput).hasValue("0");
        inputsPage.clickArrowDown();
        assertThat(inputsPage.numberInput).hasValue("-1");
    }
}
