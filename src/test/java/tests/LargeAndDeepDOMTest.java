package tests;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.LargeAndDeepDOMPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LargeAndDeepDOMTest extends BaseTest{

    private LargeAndDeepDOMPage largeAndDeepDOMPage;

    @BeforeEach
    void setupPage() {
        largeAndDeepDOMPage = new LargeAndDeepDOMPage(page);
        mainPage.largeAndDeepDOMLink.click();
    }

    @Test
    void weCanNavigateToLargeAndDeepDOMPage() {
        assertThat(largeAndDeepDOMPage.header).isVisible();
        assertThat(largeAndDeepDOMPage.header).hasText("Large & Deep DOM");
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
