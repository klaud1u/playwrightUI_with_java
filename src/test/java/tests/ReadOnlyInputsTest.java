package tests;

import config.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.ReadOnlyInputsPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
@Tag("regression")
public class ReadOnlyInputsTest extends BaseTest{
    private ReadOnlyInputsPage readOnlyInputsPage;

    @BeforeEach
    public void setupPage() {
        readOnlyInputsPage = new ReadOnlyInputsPage(page);
        mainPage.navigateToReadOnlyInputs();
    }

    @Test
    @Tag("smoke")
    void pageDisplaysDefaultFieldStates() {
        assertThat(page).hasURL(TestConfig.BASE_URL + "/readonly");
        assertThat(readOnlyInputsPage.header).hasText("Read-only Inputs");
        assertThat(readOnlyInputsPage.readOnlyCheckbox).not().isChecked();
        assertThat(readOnlyInputsPage.textField).isEditable();
        assertThat(readOnlyInputsPage.textField).hasValue("I am editable");
        assertThat(readOnlyInputsPage.alwaysReadOnlyField).isEnabled();
        assertThat(readOnlyInputsPage.alwaysReadOnlyField).not().isEditable();
        assertThat(readOnlyInputsPage.alwaysReadOnlyField).hasValue("You cannot change me");
        assertThat(readOnlyInputsPage.disabledField).isDisabled();
        assertThat(readOnlyInputsPage.disabledField).hasValue("I am disabled");
    }

    @Test
    void textFieldCanBeEditedWhenCheckboxIsUnchecked() {
        readOnlyInputsPage.textField.fill("test test");
        assertThat(readOnlyInputsPage.textField).hasValue("test test");
    }

    @Test
    void checkingCheckboxMakesTextFieldReadOnly() {
        readOnlyInputsPage.readOnlyCheckbox.check();
        assertThat(readOnlyInputsPage.readOnlyCheckbox).isChecked();
        assertThat(readOnlyInputsPage.textField).isEnabled();
        assertThat(readOnlyInputsPage.textField).not().isEditable();
        assertThat(readOnlyInputsPage.textField).hasValue("You cannot change me");
        readOnlyInputsPage.textField.click();
        page.keyboard().type("test test");
        assertThat(readOnlyInputsPage.textField).hasValue("You cannot change me");
    }

    @Test
    void uncheckingCheckboxMakesTextFieldEditableAgain() {
        readOnlyInputsPage.readOnlyCheckbox.check();
        assertThat(readOnlyInputsPage.textField).not().isEditable();
        readOnlyInputsPage.readOnlyCheckbox.uncheck();
        assertThat(readOnlyInputsPage.readOnlyCheckbox).not().isChecked();
        assertThat(readOnlyInputsPage.textField).isEditable();
        assertThat(readOnlyInputsPage.textField).hasValue("I am editable");
        readOnlyInputsPage.textField.fill("test test");
        assertThat(readOnlyInputsPage.textField).hasValue("test test");
    }

    @Test
    void checkingCheckboxReplacesPreviouslyEnteredText() {
        readOnlyInputsPage.textField.fill("test123 test");
        assertThat(readOnlyInputsPage.textField).hasValue("test123 test");
        readOnlyInputsPage.readOnlyCheckbox.check();
        assertThat(readOnlyInputsPage.textField).hasValue("You cannot change me");
    }

    @Test
    void alwaysReadOnlyFieldCannotBeChangedByTyping() {
        readOnlyInputsPage.alwaysReadOnlyField.click();
        page.keyboard().type("test test");
        assertThat(readOnlyInputsPage.alwaysReadOnlyField).hasValue("You cannot change me");
    }

    @Test
    void checkboxDoesNotChangeOtherFieldStates() {
        readOnlyInputsPage.readOnlyCheckbox.check();
        readOnlyInputsPage.readOnlyCheckbox.uncheck();
        assertThat(readOnlyInputsPage.alwaysReadOnlyField).isEnabled();
        assertThat(readOnlyInputsPage.alwaysReadOnlyField).not().isEditable();
        assertThat(readOnlyInputsPage.alwaysReadOnlyField).hasValue("You cannot change me");
        assertThat(readOnlyInputsPage.disabledField).isDisabled();
        assertThat(readOnlyInputsPage.disabledField).hasValue("I am disabled");
    }

}
