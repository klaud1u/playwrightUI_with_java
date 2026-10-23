package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.ABPage;
import pages.GetByTextPage;
import pages.MainPage;

import static java.awt.SystemColor.text;

public class ABTest extends BaseTest{
    @Test
    void clickABtest(){
        mainPage.AbTestingLink.click();
        String headerText = abPage.header.textContent().trim();
        Assertions.assertTrue(headerText.equals("A/B Test Control") || headerText.equals("A/B Test Variation 1"));
        Assertions.assertTrue(abPage.text.textContent().contains("Also known as split testing."));
    }
}
