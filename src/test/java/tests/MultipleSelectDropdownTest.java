package tests;

import com.microsoft.playwright.Locator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.MultipleSelectDropdownPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class MultipleSelectDropdownTest extends BaseTest {


    private MultipleSelectDropdownPage multipleSelectDropdownPage;

    @BeforeEach
    void setupPage() {
        multipleSelectDropdownPage = new MultipleSelectDropdownPage(page);
        mainPage.multipleSelectDropdownLink.click();
    }

    @Test
    void dropdownDisplaysAllOptionsWithoutInitialSelection() {
        assertThat(multipleSelectDropdownPage.header).hasText("Multiple Select Dropdown List");
        assertThat(multipleSelectDropdownPage.optionsList).isVisible();
        assertThat(multipleSelectDropdownPage.options).hasText(new String[]{"Option 1", "Option 2", "Option 3", "Option 4"});
        assertThat(multipleSelectDropdownPage.optionsList).hasValues(new String[]{});
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1",
            "2",
            "3",
            "4",
            "1,2",
            "1,3",
            "1,4",
            "2,3",
            "2,4",
            "3,4",
            "1,2,3",
            "1,2,4",
            "1,3,4",
            "2,3,4",
            "1,2,3,4"
    })
    void optionCombinationsCanBeSelected(String selectedOptions) {
        String[] values = selectedOptions.split(",");
        multipleSelectDropdownPage.optionsList.selectOption(values);
        assertThat(multipleSelectDropdownPage.optionsList).hasValues(values);
    }

    @Test
    void newSelectionReplacesPreviousSelection() {
        multipleSelectDropdownPage.optionsList.selectOption(new String[]{"1", "2"});
        assertThat(multipleSelectDropdownPage.optionsList).hasValues(new String[]{"1", "2"});
        multipleSelectDropdownPage.optionsList.selectOption(new String[]{"3", "4"});
        assertThat(multipleSelectDropdownPage.optionsList).hasValues(new String[]{"3", "4"});
    }

    @Test
    void allOptionsCanBeDeselected() throws InterruptedException {
        multipleSelectDropdownPage.optionsList.selectOption(new String[]{"1", "2", "3", "4"});
        assertThat(multipleSelectDropdownPage.optionsList).hasValues(new String[]{"1", "2", "3", "4"});
        multipleSelectDropdownPage.optionsList.selectOption(new String[]{});
        assertThat(multipleSelectDropdownPage.optionsList).hasValues(new String[]{});
    }
    @Test
    void clickingOptionsWithoutCtrlKeepsOnlyLastOptionSelected() {
        multipleSelectDropdownPage.option1.click();
        assertThat(multipleSelectDropdownPage.optionsList).hasValues(new String[]{"1"});
        multipleSelectDropdownPage.option2.click();
        assertThat(multipleSelectDropdownPage.optionsList).hasValues(new String[]{"2"});
        multipleSelectDropdownPage.option3.click();
        assertThat(multipleSelectDropdownPage.optionsList).hasValues(new String[]{"3"});
        multipleSelectDropdownPage.option4.click();
        assertThat(multipleSelectDropdownPage.optionsList).hasValues(new String[]{"4"});
    }
}

