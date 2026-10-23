package tests;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LargeAndDeepDOMTest extends BaseTest{

    @Test
    void weCanNavigateToLargeAndDeepDOMPage() {
        mainPage.largeAndDeepDOMLink.click();
        assertThat(largeAndDeepDOMPage.header).isVisible();
        assertThat(largeAndDeepDOMPage.header).hasText("Large & Deep DOM");
    }

    @Test
    void siblingDisplaysExpectedText() {
        mainPage.largeAndDeepDOMLink.click();
        assertThat(largeAndDeepDOMPage.sibling(50,3)).hasText("50.3");
    }

    @Test
    void tableCellDisplaysExpectedValue() {
        mainPage.largeAndDeepDOMLink.click();
        assertThat(largeAndDeepDOMPage.tableHeading).isVisible();
        assertThat(largeAndDeepDOMPage.tableCell(50, 2)).hasText("50.2");
    }

    @Test
    void tableHas50RowsAnd50Columns() {
        mainPage.largeAndDeepDOMLink.click();
        assertThat(largeAndDeepDOMPage.table).isVisible();
        Assertions.assertEquals(50, largeAndDeepDOMPage.tableRows.count());
        Assertions.assertEquals(50, largeAndDeepDOMPage.tableHeaders.count());
    }
}
