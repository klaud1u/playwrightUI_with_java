package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.AddRemovePage;
import pages.MainPage;

public class AddRemoveTest extends BaseTest{
    @Test
    void addRemoveElements(){

        mainPage.addRemoveElements.click();
        addRemovePage.AddElementBtn.click();
        Assertions.assertTrue(addRemovePage.DeleteButton.isVisible());
        addRemovePage.DeleteButton.click();
        Assertions.assertFalse(addRemovePage.DeleteButton.isVisible());

    }
}
