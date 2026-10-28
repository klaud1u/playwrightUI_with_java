package tests;

import com.microsoft.playwright.Locator;
import config.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import pages.SortableDataTablesPage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
@Tag("regression")
public class SortableDataTablesTest extends BaseTest {

   private SortableDataTablesPage sortableDataTablesPage;

    @BeforeEach
    void setupPage() {
        sortableDataTablesPage = new SortableDataTablesPage(page);
        mainPage.navigateToSortableDataTables();
    }

    @Test
    @Tag("smoke")
    void pageDisplaysExpectedContent() {
        assertThat(page).hasURL(TestConfig.BASE_URL + "/tables");
        assertThat(sortableDataTablesPage.header).hasText("Data Tables");
        assertThat(sortableDataTablesPage.table(1)).isVisible();
        assertThat(sortableDataTablesPage.table(2)).isVisible();
    }


    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    void tableDisplaysExpectedRowsAndHeaders(int tableNumber) {
        assertThat(sortableDataTablesPage.rows(tableNumber)).hasCount(4);
        assertThat(sortableDataTablesPage.headers(tableNumber)).hasText(new String[]{
                        "Last Name",
                        "First Name",
                        "Email",
                        "Due",
                        "Web Site",
                        "Action"
                });
    }

    @ParameterizedTest
    @CsvSource({
            "1, Last Name, 1",
            "1, First Name, 2",
            "1, Email, 3",
            "1, Due, 4",
            "1, Web Site, 5",
            "2, Last Name, 1",
            "2, First Name, 2",
            "2, Email, 3",
            "2, Due, 4",
            "2, Web Site, 5"
    })
    void columnCanBeSortedAscending(int tableNumber, String columnName, int columnNumber) {
        Locator column = sortableDataTablesPage.columnValues(tableNumber, columnNumber);
        assertThat(column).hasCount(4);
        System.out.println("before sort " + column.allTextContents());
        String[] expectedOrder = sortableDataTablesPage.sortedColumnValues(tableNumber, columnNumber, true);
        sortableDataTablesPage.columnHeader(tableNumber, columnName).click();
        System.out.println("after sort " + column.allTextContents());
        assertThat(column).hasText(expectedOrder);
    }

    @ParameterizedTest
    @CsvSource({
            "1, Last Name, 1",
            "1, First Name, 2",
            "1, Email, 3",
            "1, Due, 4",
            "1, Web Site, 5",
            "2, Last Name, 1",
            "2, First Name, 2",
            "2, Email, 3",
            "2, Due, 4",
            "2, Web Site, 5"
    })
    void columnCanBeSortedDescending(int tableNumber, String columnName, int columnNumber) {
        Locator column = sortableDataTablesPage.columnValues(tableNumber, columnNumber);
        assertThat(column).hasCount(4);
        System.out.println("before sort " + column.allTextContents());
        String[] expectedOrder = sortableDataTablesPage.sortedColumnValues(tableNumber, columnNumber, false);
        sortableDataTablesPage.columnHeader(tableNumber, columnName).click();
        sortableDataTablesPage.columnHeader(tableNumber, columnName).click();
        System.out.println("after sort " + column.allTextContents());
        assertThat(column).hasText(expectedOrder);
    }

}

