package tests;

import config.TestConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.BasicAuthPage;
import pages.MainPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
@Tag("regression")
public class BasicAuthTest extends BaseTest {

    private BasicAuthPage basicAuthPage;

    @BeforeEach
    void setupPage() {
        basicAuthPage = new BasicAuthPage(page);
        mainPage.navigateToBasicAuth();
    }

    @Test
    @Tag("smoke")
    void loginBasicAuthTest()
    {
        assertThat(page).hasURL(TestConfig.BASE_URL + "/basic_auth");
        assertThat(basicAuthPage.header).hasText("Basic Auth");
    }
}
