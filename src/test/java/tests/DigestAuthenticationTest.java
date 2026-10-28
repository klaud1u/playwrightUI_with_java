package tests;

import com.microsoft.playwright.*;
import config.TestConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.DigestAuthenticationPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
@Tag("regression")
public class DigestAuthenticationTest  extends BaseTest{
    private DigestAuthenticationPage digestAuthenticationPage;

    @BeforeEach
    void setupPage() {
        digestAuthenticationPage = new DigestAuthenticationPage(page);
        mainPage.navigateToDigestAuthentication();
    }

    @Test
    @Tag("smoke")
    void validCredentialsOpenDigestAuthenticationPage() {
        assertThat(digestAuthenticationPage.header).hasText("Digest Auth");
        assertThat(digestAuthenticationPage.successMessage).hasText("Congratulations! You must have the proper credentials.");
    }

}
