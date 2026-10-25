package pages;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class NestedFramesPage extends BasePage {
    public NestedFramesPage(Page page) {
        super(page);
    }

    private final FrameLocator topFrame = page.frameLocator("frame[name='frame-top']");

    public Locator leftText = topFrame.frameLocator("frame[name='frame-left']").locator("body");
    public Locator middleText = topFrame.frameLocator("frame[name='frame-middle']").locator("#content");
    public Locator rightText = topFrame.frameLocator("frame[name='frame-right']").locator("body");
    public Locator bottomText = page.frameLocator("frame[name='frame-bottom']").locator("body");
}
