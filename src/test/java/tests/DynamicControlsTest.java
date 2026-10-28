package tests;

import config.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.DynamicControlsPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
@Tag("regression")
public class DynamicControlsTest extends BaseTest{

    private DynamicControlsPage dynamicControlsPage;

    @BeforeEach
    void setupPage() {
        dynamicControlsPage = new DynamicControlsPage(page);
        mainPage.navigateToDynamicControls();
    }

    @Test
    @Tag("smoke")
    void dynamicControlPageDisplaysExpectedContent() {
        assertThat(page).hasURL(TestConfig.BASE_URL + "/dynamic_controls");
        assertThat(dynamicControlsPage.header).hasText("Dynamic Controls");
        assertThat(dynamicControlsPage.checkbox).isVisible();
        assertThat(dynamicControlsPage.checkbox).not().isChecked();
        assertThat(dynamicControlsPage.removeBtn).isEnabled();
        assertThat(dynamicControlsPage.inputField).isVisible();
        assertThat(dynamicControlsPage.inputField).isDisabled();
        assertThat(dynamicControlsPage.inputField).hasValue("");
        assertThat(dynamicControlsPage.enableBtn).isEnabled();
    }

    @Test
    void checkboxCanBeCheckedAndUnchecked() {
        dynamicControlsPage.checkbox.check();
        assertThat(dynamicControlsPage.checkbox).isChecked();
        dynamicControlsPage.checkbox.uncheck();
        assertThat(dynamicControlsPage.checkbox).not().isChecked();
    }

    @Test
    void checkboxCanBeRemovedAndAddedAgain() {
        dynamicControlsPage.checkbox.check();
        assertThat(dynamicControlsPage.checkbox).isChecked();
        dynamicControlsPage.removeBtn.click();
        assertThat(dynamicControlsPage.checkboxLoading).isVisible();
        assertThat(dynamicControlsPage.removeBtn).isDisabled();
        assertThat(dynamicControlsPage.checkboxMessage).hasText("It's gone!");
        assertThat(dynamicControlsPage.checkboxLoading).isHidden();
        assertThat(dynamicControlsPage.addBtn).isEnabled();
        assertThat(dynamicControlsPage.checkbox).hasCount(0);
        dynamicControlsPage.addBtn.click();
        assertThat(dynamicControlsPage.checkboxLoading).isVisible();
        assertThat(dynamicControlsPage.addBtn).isDisabled();
        assertThat(dynamicControlsPage.checkboxMessage).hasText("It's back!");
        assertThat(dynamicControlsPage.checkbox).isVisible();
        assertThat(dynamicControlsPage.checkbox).not().isChecked();
        assertThat(dynamicControlsPage.checkboxLoading).isHidden();
        assertThat(dynamicControlsPage.removeBtn).isEnabled();
        dynamicControlsPage.checkbox.check();
        assertThat(dynamicControlsPage.checkbox).isChecked();
    }

    @Test
    void enabledInputCanBeEdited() {
        dynamicControlsPage.enableBtn.click();
        assertThat(dynamicControlsPage.inputLoading).isVisible();
        assertThat(dynamicControlsPage.enableBtn).isDisabled();
        assertThat(dynamicControlsPage.inputMessage).hasText("It's enabled!");
        assertThat(dynamicControlsPage.inputField).isEditable();
        assertThat(dynamicControlsPage.inputLoading).isHidden();
        assertThat(dynamicControlsPage.disableBtn).isEnabled();
        dynamicControlsPage.inputField.fill("Test text");
        assertThat(dynamicControlsPage.inputField).hasValue("Test text");
    }

    @Test
    void disablingInputKeepsTextAndPreventsEditing() {
        dynamicControlsPage.enableBtn.click();
        assertThat(dynamicControlsPage.inputField).isEditable();
        dynamicControlsPage.inputField.fill("Test text");
        assertThat(dynamicControlsPage.inputField).hasValue("Test text");
        dynamicControlsPage.disableBtn.click();
        assertThat(dynamicControlsPage.inputLoading).isVisible();
        assertThat(dynamicControlsPage.disableBtn).isDisabled();
        assertThat(dynamicControlsPage.inputMessage).hasText("It's disabled!");
        assertThat(dynamicControlsPage.inputField).isDisabled();
        assertThat(dynamicControlsPage.inputField).not().isEditable();
        assertThat(dynamicControlsPage.inputField).hasValue("Test text");
        assertThat(dynamicControlsPage.inputLoading).isHidden();
        assertThat(dynamicControlsPage.enableBtn).isEnabled();
        dynamicControlsPage.enableBtn.click();
        assertThat(dynamicControlsPage.inputMessage).hasText("It's enabled!");
        assertThat(dynamicControlsPage.inputField).isEditable();
        assertThat(dynamicControlsPage.inputField).hasValue("Test text");
        dynamicControlsPage.inputField.fill("Updated text");
        assertThat(dynamicControlsPage.inputField).hasValue("Updated text");
    }
}
