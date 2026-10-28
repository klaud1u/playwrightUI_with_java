package tests;

import config.TestConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.CheckboxesPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
@Tag("regression")
public class CheckBoxesTest extends BaseTest {

    private CheckboxesPage checkboxesPage;

    @BeforeEach
    void setupPage() {
        checkboxesPage = new CheckboxesPage(page);
        mainPage.navigateToCheckboxes();
    }

    @Test
    @Tag("smoke")
    public void navigateToCheckboxesPage() {
        assertThat(page).hasURL(TestConfig.BASE_URL + "/checkboxes");
        assertThat(checkboxesPage.header).hasText("Checkboxes");
        assertThat(checkboxesPage.firstCheckbox).isVisible();
        assertThat(checkboxesPage.secondCheckbox).isVisible();
        assertThat(checkboxesPage.secondCheckbox).isChecked();
        assertThat(checkboxesPage.firstCheckbox).not().isChecked();
    }


@Test
void shouldCheckFirstCheckbox(){
       assertThat(checkboxesPage.firstCheckbox).not().isChecked();
       checkboxesPage.firstCheckbox.click();
       assertThat(checkboxesPage.firstCheckbox).isChecked();
}
@Test
    void shouldUncheckSecondCheckbox(){
    assertThat(checkboxesPage.secondCheckbox).isChecked();
    checkboxesPage.secondCheckbox.click();
    assertThat(checkboxesPage.secondCheckbox).not().isChecked();
}
@Test
    void checkingFirstCheckboxShouldNotChangeSecondCheckbox(){
    assertThat(checkboxesPage.firstCheckbox).not().isChecked();
    assertThat(checkboxesPage.secondCheckbox).isChecked();
    checkboxesPage.firstCheckbox.click();
    assertThat(checkboxesPage.firstCheckbox).isChecked();
    assertThat(checkboxesPage.secondCheckbox).isChecked();
}
@Test
    void uncheckingSecondCheckboxShouldNotChangeFirstCheckbox(){
    assertThat(checkboxesPage.firstCheckbox).not().isChecked();
    assertThat(checkboxesPage.secondCheckbox).isChecked();
    checkboxesPage.secondCheckbox.click();
    assertThat(checkboxesPage.firstCheckbox).not().isChecked();
    assertThat(checkboxesPage.secondCheckbox).not().isChecked();
}
}
