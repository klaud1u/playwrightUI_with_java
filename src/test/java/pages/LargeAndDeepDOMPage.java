package pages;


import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class LargeAndDeepDOMPage extends BasePage {
    public LargeAndDeepDOMPage(Page page) {
        super(page);
    }
    public Locator header = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Large & Deep DOM").setExact(true));
    public Locator deepestSibling = page.locator("[id='sibling-50.3']");
    public Locator table = page.locator("#large-table");
    public Locator tableRows = table.locator("tbody tr");
    public Locator tableHeading = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Table").setExact(true));
    public Locator tableHeaders = table.locator("thead th");
    public Locator lastTableCell = page.locator("#large-table tbody tr.row-50 td.column-50");

    public Locator sibling(int level, int item) {
        return page.locator("[id='sibling-" + level + "." + item + "']");
    }

    public Locator tableCell(int row, int column) {
        return page.locator("#large-table tr.row-" + row + " td.column-" + column);
    }
}

