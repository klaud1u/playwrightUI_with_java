package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.ABPage;
import pages.GetByTextPage;
import pages.MainPage;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static java.awt.SystemColor.text;

public class ABTest extends BaseTest{
    @Test
    void clickABtest(){
        mainPage.AbTestingLink.click();
        assertThat(abPage.header).hasText(Pattern.compile("^A/B Test (Control|Variation 1)$"));
        assertThat(abPage.text).containsText("Also known as split testing.");
    }
}
