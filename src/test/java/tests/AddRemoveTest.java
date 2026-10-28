package tests;

import config.TestConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.AddRemovePage;
import pages.MainPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Tag("regression")
public class AddRemoveTest extends BaseTest{

    private AddRemovePage addRemovePage;

    @BeforeEach
    void setupPage() {
        addRemovePage = new AddRemovePage(page);
        mainPage.navigateToAddRemoveElements();
    }


    @Test
    @Tag("smoke")
    void addRemoveDisplaysExpectedContent(){
        assertThat(page).hasURL(TestConfig.BASE_URL + "/add_remove_elements/");
        assertThat(addRemovePage.header).hasText("Add/Remove Elements");
        assertThat(addRemovePage.addElementBtn).isVisible();
        assertThat(addRemovePage.deleteBtn).isHidden();

    }


    @Test
    @Tag("smoke")
    void addRemoveElements(){
        addRemovePage.addElement();
        assertThat(addRemovePage.addElementBtn).isVisible();
        addRemovePage.deleteElement();
        assertThat(addRemovePage.deleteBtn).isHidden();
    }

    @Test
    void multipleElementsCanBeAdded() {
       addRemovePage.addElements(5);
        assertThat(addRemovePage.deleteBtn).hasCount(5);
    }

    @Test
    void deletingOneElementKeepsTheOthers() {
        addRemovePage.addElements(3);
        assertThat(addRemovePage.deleteBtn).hasCount(3);
        addRemovePage.deleteFirstElement();
        assertThat(addRemovePage.deleteBtn).hasCount(2);
    }

    @Test
    void allAddedElementsCanBeDeleted() {
        addRemovePage.addElements(5);
        assertThat(addRemovePage.deleteBtn).hasCount(5);
        addRemovePage.deleteElements(5);
        assertThat(addRemovePage.deleteBtn).hasCount(0);
    }

}