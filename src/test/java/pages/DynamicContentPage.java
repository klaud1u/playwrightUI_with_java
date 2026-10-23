package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class DynamicContentPage extends BasePage {
    public Locator dynamicContentLink;

    public DynamicContentPage(Page page){
        super(page);
    }
    public Locator header = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Dynamic Content").setExact(true));
    public Locator avatars = page.locator(".example img");
    public Locator firstAvatar = avatars.nth(0);
    public Locator secondAvatar = avatars.nth(1);
    public Locator thirdAvatar = avatars.nth(2);
    public Locator avatarTexts = page.locator(".example > .row #content .row .large-10.columns");
    public Locator firstPhotoText = avatarTexts.nth(0);
    public Locator secondPhotoText = avatarTexts.nth(1);
    public Locator thirdPhotoText = avatarTexts.nth(2);

    public void waitForImagesToFinishLoading() {
        page.waitForFunction(
                "() => [...document.querySelectorAll('.example img')].every(img => img.complete)"
        );
    }
}
