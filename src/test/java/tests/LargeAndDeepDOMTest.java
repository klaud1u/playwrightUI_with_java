package tests;


import config.TestConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.LargeAndDeepDOMPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
@Tag("regression")
public class LargeAndDeepDOMTest extends BaseTest{

    private LargeAndDeepDOMPage largeAndDeepDOMPage;

    @BeforeEach
    void setupPage() {
        largeAndDeepDOMPage = new LargeAndDeepDOMPage(page);
        mainPage.navigateToLargeAndDeepDOM();
    }

    @Test
    @Tag("smoke")
    void weCanNavigateToLargeAndDeepDOMPage() {
        assertThat(page).hasURL(TestConfig.BASE_URL + "/large");
        assertThat(largeAndDeepDOMPage.header).hasText("Large & Deep DOM");
        assertThat(largeAndDeepDOMPage.tableHeading).hasText("Table");
        assertThat(largeAndDeepDOMPage.table).isVisible();
    }

    @Test
    void siblingDisplaysExpectedText() {
        assertThat(largeAndDeepDOMPage.sibling(50,3)).hasText("50.3");
    }

    @Test
    void tableCellDisplaysExpectedValue() {
        assertThat(largeAndDeepDOMPage.tableHeading).isVisible();
        assertThat(largeAndDeepDOMPage.tableCell(50, 2)).hasText("50.2");
    }

    @Test
    void tableHas50RowsAnd50Columns() {
        assertThat(largeAndDeepDOMPage.table).isVisible();
        assertThat(largeAndDeepDOMPage.tableRows).hasCount(50);
        assertThat(largeAndDeepDOMPage.tableHeaders).hasCount(50);
    }
}
