package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.CheckboxesPage;

public class CheckBoxesTest extends BaseTest {

    private CheckboxesPage checkboxesPage;

    @BeforeEach
    void setupPage() {
        checkboxesPage = new CheckboxesPage(page);
        mainPage.checkboxLink.click();
    }

    @Test
    public void navigateToCheckboxesPage() {

        Assertions.assertEquals("Checkboxes", checkboxesPage.header.textContent().trim());
        Assertions.assertTrue(checkboxesPage.firstCheckbox.isVisible());
        Assertions.assertTrue(checkboxesPage.secondCheckbox.isVisible());
    }

    @Test
    public void secondCheckBoxIsCheckedWhenWeNavigateToCheckboxesPage() {

        Assertions.assertTrue(checkboxesPage.secondCheckbox.isChecked());
    }
    @Test
    public void firstCheckBoxIsNotCheckedWhenWeNavigateToCheckboxesPage() {

        Assertions.assertFalse(checkboxesPage.firstCheckbox.isChecked());
    }
@Test
void shouldCheckFirstCheckbox(){
       Assertions.assertFalse(checkboxesPage.firstCheckbox.isChecked());
       checkboxesPage.firstCheckbox.click();
       Assertions.assertTrue(checkboxesPage.firstCheckbox.isChecked());
}
@Test
    void shouldUncheckSecondCheckbox(){
    Assertions.assertTrue(checkboxesPage.secondCheckbox.isChecked());
    checkboxesPage.secondCheckbox.click();
    Assertions.assertFalse(checkboxesPage.secondCheckbox.isChecked());
}
@Test
    void checkingFirstCheckboxShouldNotChangeSecondCheckbox(){
    Assertions.assertFalse(checkboxesPage.firstCheckbox.isChecked());
    Assertions.assertTrue(checkboxesPage.secondCheckbox.isChecked());
    checkboxesPage.firstCheckbox.click();
    Assertions.assertTrue(checkboxesPage.firstCheckbox.isChecked());
    Assertions.assertTrue(checkboxesPage.secondCheckbox.isChecked());
}
@Test
    void uncheckingSecondCheckboxShouldNotChangeFirstCheckbox(){
    Assertions.assertFalse(checkboxesPage.firstCheckbox.isChecked());
    Assertions.assertTrue(checkboxesPage.secondCheckbox.isChecked());
    checkboxesPage.secondCheckbox.click();
    Assertions.assertFalse(checkboxesPage.firstCheckbox.isChecked());
    Assertions.assertFalse(checkboxesPage.secondCheckbox.isChecked());
}
}
