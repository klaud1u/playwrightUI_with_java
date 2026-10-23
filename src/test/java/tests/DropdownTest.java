package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DropdownTest extends BaseTest {

    @Test
    void dropdownShowsExpectedOptions() {
        mainPage.dropdownLink.click();
        Assertions.assertEquals("Dropdown List", dropdownPage.header.textContent().trim());
        Assertions.assertEquals(3, dropdownPage.dropdown.locator("option").count());
    }

    @Test
    void weCanSelectOption1() {
        mainPage.dropdownLink.click();
        dropdownPage.dropdown.selectOption("1");
        Assertions.assertEquals("1", dropdownPage.dropdown.inputValue());
    }
    @Test
    void weCanSelectOption2() {
        mainPage.dropdownLink.click();
        dropdownPage.dropdown.selectOption("2");
        Assertions.assertEquals("2", dropdownPage.dropdown.inputValue());
    }


}
