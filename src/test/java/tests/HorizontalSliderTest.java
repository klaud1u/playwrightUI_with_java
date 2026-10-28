package tests;

import config.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.HorizontalSliderPage;

import java.math.BigDecimal;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
@Tag("regression")
public class HorizontalSliderTest extends BaseTest {

    private HorizontalSliderPage horizontalSliderPage;

    @BeforeEach
    void setupPage() {
        horizontalSliderPage = new HorizontalSliderPage(page);
        mainPage.navigateToHorizontalSlider();
    }

    @Test
    @Tag("smoke")
    void HorizontalSliderPageDisplaysExpectedContent() {
        assertThat(page).hasURL(TestConfig.BASE_URL + "/horizontal_slider");
        assertThat(horizontalSliderPage.header).hasText("Horizontal Slider");
        assertThat(horizontalSliderPage.slider).isVisible();
        assertThat(horizontalSliderPage.slider).isEnabled();
        assertThat(horizontalSliderPage.slider).hasValue("0");
        assertThat(horizontalSliderPage.sliderValue).hasText("0");
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.5, 1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0, 4.5, 5.0,0.0})
    void sliderCanBeSetToValueByClicking(double sliderValue) {
        horizontalSliderPage.setSliderValueByClick(sliderValue);
        String expectedValue = BigDecimal.valueOf(sliderValue).stripTrailingZeros().toPlainString();
        assertThat(horizontalSliderPage.sliderValue).hasText(expectedValue);
    }

    @Test
    void sliderMovesFromMinimumToMaximumAndBack(){
        horizontalSliderPage.moveRight(10);
        assertThat(horizontalSliderPage.sliderValue).hasText("5");
        horizontalSliderPage.moveLeft(10);
        assertThat(horizontalSliderPage.sliderValue).hasText("0");
    }

    @Test
    void sliderCanBeMovedByDragging() {
        assertThat(horizontalSliderPage.sliderValue).hasText("0");
        horizontalSliderPage.dragSliderTo(5.0);
        assertThat(horizontalSliderPage.sliderValue).hasText("5");
    }
}
