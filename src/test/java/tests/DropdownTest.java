package tests;

import config.TestConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.DropdownPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
@Tag("regression")
public class DropdownTest extends BaseTest {
    private DropdownPage dropdownPage;

    @BeforeEach
    void setupPage() {
        dropdownPage = new DropdownPage(page);
        mainPage.navigateToDropdown();

    }



    @Test
    @Tag("smoke")
    void dropdownPageDisplaysExpectedContent() {
        assertThat(page).hasURL(TestConfig.BASE_URL + "/dropdown");
        assertThat(dropdownPage.header).hasText("Dropdown List");
        assertThat(dropdownPage.dropdown).isVisible();
        assertThat(dropdownPage.dropdown).hasValue("");
        assertThat(dropdownPage.dropdown.locator("option")).hasText(new String[]{"Please select an option", "Option 1", "Option 2"});
    }

    @Test
    void weCanSelectOption1() {
        dropdownPage.dropdown.selectOption("1");
        assertThat(dropdownPage.dropdown).hasValue("1");
    }
    @Test
    void weCanSelectOption2() {
        dropdownPage.dropdown.selectOption("2");
        assertThat(dropdownPage.dropdown).hasValue("2");
    }


}
