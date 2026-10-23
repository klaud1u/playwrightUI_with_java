package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.BasicAuthPage;
import pages.MainPage;

public class BasicAuthTest extends BaseTest {

    @Test
    void loginBasicAuthTest()
    {
        mainPage.basicAuthLink.click();
        Assertions.assertEquals("Basic Auth", basicAuthPage.basicAuthHeader.textContent().trim());

    }
}
