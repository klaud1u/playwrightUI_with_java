package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CheckBoxesTest extends BaseTest {
    @Test
    public void navigateToCheckboxesPage() {
        mainPage.checkboxLink.click();
        Assertions.assertEquals("Checkboxes", checkboxesPage.header.textContent().trim());
        Assertions.assertTrue(checkboxesPage.firstCheckbox.isVisible());
        Assertions.assertTrue(checkboxesPage.secondCheckbox.isVisible());
    }

    @Test
    public void secondCheckBoxIsCheckedWhenWeNavigateToCheckboxesPage() {
        mainPage.checkboxLink.click();
        Assertions.assertTrue(checkboxesPage.secondCheckbox.isChecked());
    }
    @Test
    public void firstCheckBoxIsNotCheckedWhenWeNavigateToCheckboxesPage() {
        mainPage.checkboxLink.click();
        Assertions.assertFalse(checkboxesPage.firstCheckbox.isChecked());
    }
@Test
void shouldCheckFirstCheckbox(){
       mainPage.checkboxLink.click();
       Assertions.assertFalse(checkboxesPage.firstCheckbox.isChecked());
       checkboxesPage.firstCheckbox.click();
       Assertions.assertTrue(checkboxesPage.firstCheckbox.isChecked());
}
@Test
    void shouldUncheckSecondCheckbox(){
    mainPage.checkboxLink.click();
    Assertions.assertTrue(checkboxesPage.secondCheckbox.isChecked());
    checkboxesPage.secondCheckbox.click();
    Assertions.assertFalse(checkboxesPage.secondCheckbox.isChecked());
}
@Test
    void checkingFirstCheckboxShouldNotChangeSecondCheckbox(){
    mainPage.checkboxLink.click();
    Assertions.assertFalse(checkboxesPage.firstCheckbox.isChecked());
    Assertions.assertTrue(checkboxesPage.secondCheckbox.isChecked());
    checkboxesPage.firstCheckbox.click();
    Assertions.assertTrue(checkboxesPage.firstCheckbox.isChecked());
    Assertions.assertTrue(checkboxesPage.secondCheckbox.isChecked());
}
@Test
    void uncheckingSecondCheckboxShouldNotChangeFirstCheckbox(){
    mainPage.checkboxLink.click();
    Assertions.assertFalse(checkboxesPage.firstCheckbox.isChecked());
    Assertions.assertTrue(checkboxesPage.secondCheckbox.isChecked());
    checkboxesPage.secondCheckbox.click();
    Assertions.assertFalse(checkboxesPage.firstCheckbox.isChecked());
    Assertions.assertFalse(checkboxesPage.secondCheckbox.isChecked());
}
}
