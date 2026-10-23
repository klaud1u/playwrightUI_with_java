package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Mouse;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.BoundingBox;

public class HorizontalSliderPage extends BasePage {
    public HorizontalSliderPage(Page page) {
        super(page);
    }
    public Locator header = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Horizontal Slider"));
    public Locator slider = page.getByRole(AriaRole.SLIDER);
    public Locator sliderValue = page.locator("#range");



    public void setSliderValueByClick(double targetValue) {
        double min = Double.parseDouble(slider.getAttribute("min"));
        double max = Double.parseDouble(slider.getAttribute("max"));
        BoundingBox box = slider.boundingBox();
        double position = (targetValue - min) / (max - min);
        double thumbMargin = 8;
        double usableWidth = box.width - 2 * thumbMargin;
        double clickX = box.x + thumbMargin + usableWidth * position;
        double clickY = box.y + box.height / 2;
        page.mouse().click(clickX, clickY);
    }

    public void moveRight(int steps) {
        for (int i = 0; i < steps; i++) {
            slider.press("ArrowRight");
        }
    }

    public void moveLeft(int steps) {
        for (int i = 0; i < steps; i++) {
            slider.press("ArrowLeft");
        }
    }

    public void dragSliderTo(double targetValue) {
        double min = Double.parseDouble(slider.getAttribute("min"));
        double max = Double.parseDouble(slider.getAttribute("max"));
        BoundingBox box = slider.boundingBox();
        double thumbMargin = 8;
        double usableWidth = box.width - 2 * thumbMargin;
        double position = (targetValue - min) / (max - min);
        double y = box.y + box.height / 2;
        double startX = box.x + thumbMargin;
        double targetX = box.x + thumbMargin + usableWidth * position;
        page.mouse().move(startX, y);
        page.mouse().down();
        page.mouse().move(targetX, y, new Mouse.MoveOptions().setSteps(10));
        page.mouse().up();
    }
}



