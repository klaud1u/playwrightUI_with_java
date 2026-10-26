package tests;

import config.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.SubdirectoriesPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class SubdirectoriesTest extends BaseTest {

    private SubdirectoriesPage subdirectoriesPage;

    @BeforeEach
    void setupPage() {
        subdirectoriesPage = new SubdirectoriesPage(page);
        mainPage.subdirectoriesLink.click();
    }

    @Test
    void mainPageDisplaysExpectedContent() {
        assertThat(page).hasURL(TestConfig.BASE_URL + "/subdirectories");
        assertThat(subdirectoriesPage.header).hasText("Subdirectories");
        assertThat(subdirectoriesPage.currentPath).hasText("/subdirectories");
        assertThat(subdirectoriesPage.navigationLinks).hasText(new String[]{"/subdirectories/one", "/subdirectories/two"});
    }

    @ParameterizedTest
    @CsvSource({
            "one, Subdirectory One, two",
            "two, Subdirectory Two, one"
    })
    void openingSubdirectoryUpdatesPage(String directory, String expectedHeading, String otherDirectory) {
        subdirectoriesPage.subdirectoriesLink(directory).click();
        assertThat(page).hasURL(TestConfig.BASE_URL + "/subdirectories/" + directory);
        assertThat(subdirectoriesPage.header).hasText(expectedHeading);
        assertThat(subdirectoriesPage.currentPath).hasText("/subdirectories/" + directory);
        assertThat(subdirectoriesPage.navigationLinks).hasText(new String[]{"/subdirectories", "/subdirectories/" + otherDirectory});
    }

    @ParameterizedTest
    @CsvSource({
            "one, two, Subdirectory Two",
            "two, one, Subdirectory One"
    })
    void canNavigateBetweenSubdirectories(String firstDirectory, String nextDirectory, String expectedHeading) {
        subdirectoriesPage.subdirectoriesLink(firstDirectory).click();
        assertThat(subdirectoriesPage.currentPath).hasText("/subdirectories/" + firstDirectory);
        subdirectoriesPage.subdirectoriesLink(nextDirectory).click();
        assertThat(page).hasURL(TestConfig.BASE_URL + "/subdirectories/" + nextDirectory);
        assertThat(subdirectoriesPage.header).hasText(expectedHeading);
        assertThat(subdirectoriesPage.currentPath).hasText("/subdirectories/" + nextDirectory);
        assertThat(subdirectoriesPage.navigationLinks).hasText(new String[]{"/subdirectories", "/subdirectories/" + firstDirectory
        });
    }

    @ParameterizedTest
    @CsvSource({
            "one",
            "two"
    })
    void backLinkReturnsToMainPage(String directory) {
        subdirectoriesPage.subdirectoriesLink(directory).click();
        assertThat(subdirectoriesPage.currentPath).hasText("/subdirectories/" + directory);
        subdirectoriesPage.backLink.click();
        assertThat(page).hasURL(TestConfig.BASE_URL + "/subdirectories");
        assertThat(subdirectoriesPage.header).hasText("Subdirectories");
        assertThat(subdirectoriesPage.currentPath).hasText("/subdirectories");
        assertThat(subdirectoriesPage.navigationLinks).hasText(new String[]{"/subdirectories/one", "/subdirectories/two"});
    }
}
