package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class BrokenImagesPage extends BasePage {

    public BrokenImagesPage(Page page) {
        super(page);

    }

    public Locator images = page.locator("#content img");
    public Locator firstBrokenImage = page.locator("img[src$='asdf.jpg']");
    public Locator secondBrokenImage = page.locator("img[src$='hjkl.jpg']");

    public void waitForImagesToFinishLoading() {
        page.waitForFunction("""
        () => {
            const images = [...document.querySelectorAll('#content img')];
            return images.length >0 && images.every(img => img.complete);
        }
        """);
    }

}
