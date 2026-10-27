package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.DropdownPage;

public class DropdownTest extends BaseTest {
    private DropdownPage dropdownPage;

    @BeforeEach
    void setupPage() {
        dropdownPage = new DropdownPage(page);
        mainPage.dropdownLink.click();

    }

    @Test
    void dropdownShowsExpectedOptions() {
        Assertions.assertEquals("Dropdown List", dropdownPage.header.textContent().trim());
        Assertions.assertEquals(3, dropdownPage.dropdown.locator("option").count());
    }

    @Test
    void weCanSelectOption1() {
        dropdownPage.dropdown.selectOption("1");
        Assertions.assertEquals("1", dropdownPage.dropdown.inputValue());
    }
    @Test
    void weCanSelectOption2() {
        dropdownPage.dropdown.selectOption("2");
        Assertions.assertEquals("2", dropdownPage.dropdown.inputValue());
    }


}
