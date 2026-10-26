package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortableDataTablesPage extends BasePage{
    public SortableDataTablesPage(Page page) {
        super(page);
    }

    public Locator header = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Data Tables").setExact(true));

    public Locator table(int tableNumber) {
        return page.locator("#table" + tableNumber);
    }

    public Locator rows(int tableNumber) {
        return table(tableNumber).locator("tbody tr");
    }

    public Locator headers(int tableNumber) {
        return table(tableNumber).getByRole(AriaRole.COLUMNHEADER);
    }

    public Locator columnHeader(int tableNumber, String columnName) {
        return table(tableNumber).getByRole(AriaRole.COLUMNHEADER, new Locator.GetByRoleOptions().setName(columnName).setExact(true));
    }

    public Locator columnValues(int tableNumber, int columnNumber) {
        return table(tableNumber).locator("tbody tr td:nth-child(" + columnNumber + ")");
    }

    public String[] sortedColumnValues(int tableNumber, int columnNumber, boolean ascending) {
        List<String> values = new ArrayList<>(columnValues(tableNumber, columnNumber).allTextContents());
        Comparator<String> comparator;

        if (columnNumber == 4) {
            comparator = Comparator.comparing(value -> new BigDecimal(value.trim().replace("$", "")));
        }
        else {
            comparator = String.CASE_INSENSITIVE_ORDER;
        }
        values.sort(ascending ? comparator : comparator.reversed());
        return values.toArray(new String[0]);
    }
}

