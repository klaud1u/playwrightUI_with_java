package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.AddRemovePage;
import pages.MainPage;

public class AddRemoveTest extends BaseTest{

    private AddRemovePage addRemovePage;

    @BeforeEach
    void setupPage() {
        addRemovePage = new AddRemovePage(page);
        mainPage.addRemoveElements.click();
    }
    @Test
    void addRemoveElements(){
        addRemovePage.AddElementBtn.click();
        Assertions.assertTrue(addRemovePage.DeleteButton.isVisible());
        addRemovePage.DeleteButton.click();
        Assertions.assertFalse(addRemovePage.DeleteButton.isVisible());

    }
}
