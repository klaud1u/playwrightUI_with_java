package tests;

import com.microsoft.playwright.assertions.LocatorAssertions;
import config.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import pages.DynamicLoadingPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
@Tag("regression")
public class DynamicLoadingTest extends BaseTest{

    private DynamicLoadingPage dynamicLoadingPage;

    @BeforeEach
    void setupPage() {
        dynamicLoadingPage = new DynamicLoadingPage(page);
        mainPage.navigateToDynamicLoading();
    }

    @Test
    @Tag("smoke")
    void dynamicLoadingPageDisplaysExpectedContent() {
        assertThat(page).hasURL(TestConfig.BASE_URL + "/dynamic_loading");
        assertThat(dynamicLoadingPage.header).hasText("Dynamically Loaded Page Elements");
        assertThat(dynamicLoadingPage.exampleOneLink).isVisible();
        assertThat(dynamicLoadingPage.exampleTwoLink).isVisible();
    }

    @ParameterizedTest
    @CsvSource({"1, Example 1: Element on page that is hidden", "2, Example 2: Element rendered after the fact"})
    void eachExamplePageShowsStartButton(int exampleNumber, String heading) {
        dynamicLoadingPage.exampleLink(exampleNumber).click();
        assertThat(page).hasURL(TestConfig.BASE_URL + "/dynamic_loading/" + exampleNumber);
        assertThat(dynamicLoadingPage.exampleHeading).hasText(heading);
        assertThat(dynamicLoadingPage.startBtn).isVisible();
        assertThat(dynamicLoadingPage.loadingIndicator).isHidden();
        assertThat(dynamicLoadingPage.helloText).isHidden();
    }

    @Test
    void exampleOneHelloTextExistsButIsHidden() {
        dynamicLoadingPage.exampleOneLink.click();
        assertThat(dynamicLoadingPage.helloText).hasText("Hello World!");
        assertThat(dynamicLoadingPage.helloText).hasCount(1);
        assertThat(dynamicLoadingPage.helloText).isHidden();
    }

    @Test
    void exampleTwoHelloTextDoesNotExistBeforeStart() {
        dynamicLoadingPage.exampleTwoLink.click();
        assertThat(dynamicLoadingPage.exampleHeading).hasText("Example 2: Element rendered after the fact");
        assertThat(dynamicLoadingPage.startBtn).isVisible();
        assertThat(dynamicLoadingPage.helloText).hasCount(0);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    void clickingStartShowsLoadingThenHelloWorld(int exampleNumber) {
        dynamicLoadingPage.exampleLink(exampleNumber).click();
        dynamicLoadingPage.startBtn.click();
        assertThat(dynamicLoadingPage.startBtn).isHidden();
        assertThat(dynamicLoadingPage.loadingIndicator).isVisible();
        assertThat(dynamicLoadingPage.loadingIndicator).hasText("Loading...");
        assertThat(dynamicLoadingPage.helloText).isHidden();
        assertThat(dynamicLoadingPage.helloText).isVisible(new LocatorAssertions.IsVisibleOptions().setTimeout(10000));
        assertThat(dynamicLoadingPage.helloText).hasText("Hello World!");
        assertThat(dynamicLoadingPage.loadingIndicator).isHidden();
        assertThat(dynamicLoadingPage.startBtn).isHidden();
    }
}
