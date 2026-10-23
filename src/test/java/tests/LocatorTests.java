package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.ABPage;
import pages.LocatorPage;

public class LocatorTests extends BaseTest {

    @Test
    void locatorTest(){
        ABPage abPage =new ABPage(page);
        //abPage.abTestingLink.click();

        LocatorPage locatorPage =new LocatorPage(page);
        Assertions.assertTrue(locatorPage.idLocator.isVisible());
    }
}
