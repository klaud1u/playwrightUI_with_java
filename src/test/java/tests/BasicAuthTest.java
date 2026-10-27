package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.BasicAuthPage;
import pages.MainPage;

public class BasicAuthTest extends BaseTest {

    private BasicAuthPage basicAuthPage;

    @BeforeEach
    void setupPage() {
        basicAuthPage = new BasicAuthPage(page);
        mainPage.basicAuthLink.click();
    }

    @Test
    void loginBasicAuthTest()
    {
        Assertions.assertEquals("Basic Auth", basicAuthPage.basicAuthHeader.textContent().trim());

    }
}
