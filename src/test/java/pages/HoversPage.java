package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class HoversPage extends BasePage {
    public HoversPage(Page page) {
        super(page);
    }

    private Locator userFigure(int index) {
        return page.locator(".figure").nth(index);
    }

    public Locator userImage(int index) {
        return userFigure(index).getByRole(AriaRole.IMG);
    }

    public Locator userName(int index) {
        return userFigure(index).getByRole(AriaRole.HEADING);
    }

    public Locator userProfileLink(int index) {
        return userFigure(index).getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("View profile").setExact(true)
        );
    }
}
