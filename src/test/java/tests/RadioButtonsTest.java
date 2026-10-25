package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import pages.RadioButtonsPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class RadioButtonsTest extends BaseTest {
    private RadioButtonsPage radioButtonsPage;
    @BeforeEach
    public void setupPage() {
        radioButtonsPage = new RadioButtonsPage(page);
        mainPage.radioButtonsLink.click();
    }

    @Test
    void pageDisplaysDefaultSelections() {
        assertThat(radioButtonsPage.header).hasText("Radio Buttons");
        assertThat(radioButtonsPage.blue).isChecked();
        assertThat(radioButtonsPage.red).not().isChecked();
        assertThat(radioButtonsPage.green).not().isChecked();
        assertThat(radioButtonsPage.football).not().isChecked();
        assertThat(radioButtonsPage.tennis).not().isChecked();
        assertThat(radioButtonsPage.hockey).not().isChecked();
        assertThat(radioButtonsPage.result).hasText("Color: blue, Sport: none");
    }

    @ParameterizedTest
    @CsvSource({
            "red, football",
            "red, tennis",
            "blue, football",
            "blue, tennis",
            "green, football",
            "green, tennis"
    })
    void colorAndSportCanBeSelected(String color, String sport) {
        radioButtonsPage.colorOption(color).check();
        radioButtonsPage.sportOption(sport).check();
        assertThat(radioButtonsPage.colorOption(color)).isChecked();
        assertThat(radioButtonsPage.sportOption(sport)).isChecked();
        assertThat(radioButtonsPage.result).hasText("Color: " + color + ", Sport: " + sport);
    }

    @ParameterizedTest
    @CsvSource({
            "red, green",
            "green, blue",
            "blue, red"
    })
    void selectingAnotherColorDeselectsPreviousColor(String firstColor, String secondColor) {
        radioButtonsPage.tennis.check();
        radioButtonsPage.colorOption(firstColor).check();
        assertThat(radioButtonsPage.colorOption(firstColor)).isChecked();
        radioButtonsPage.colorOption(secondColor).check();
        assertThat(radioButtonsPage.colorOption(secondColor)).isChecked();
        assertThat(radioButtonsPage.colorOption(firstColor)).not().isChecked();
        assertThat(radioButtonsPage.tennis).isChecked();
        assertThat(radioButtonsPage.result).hasText("Color: " + secondColor + ", Sport: tennis");
    }

    @ParameterizedTest
    @CsvSource({
            "football, tennis",
            "tennis, football"
    })
    void selectingAnotherSportDeselectsPreviousSport(String firstSport, String secondSport) {
        radioButtonsPage.sportOption(firstSport).check();
        assertThat(radioButtonsPage.sportOption(firstSport)).isChecked();
        radioButtonsPage.sportOption(secondSport).check();
        assertThat(radioButtonsPage.sportOption(secondSport)).isChecked();
        assertThat(radioButtonsPage.sportOption(firstSport)).not().isChecked();
        assertThat(radioButtonsPage.blue).isChecked();
        assertThat(radioButtonsPage.result).hasText("Color: blue, Sport: " + secondSport);
    }

    @ParameterizedTest
    @ValueSource(strings = {"red", "blue", "green"})
    void clickingSelectedColorDoesNotDeselectIt(String color) {
        radioButtonsPage.colorOption(color).click();
        assertThat(radioButtonsPage.colorOption(color)).isChecked();
        radioButtonsPage.colorOption(color).click();
        assertThat(radioButtonsPage.colorOption(color)).isChecked();
        assertThat(radioButtonsPage.result).hasText("Color: " + color + ", Sport: none");
    }

    @ParameterizedTest
    @ValueSource(strings = {"football", "tennis"})
    void clickingSelectedSportDoesNotDeselectIt(String sport) {
        radioButtonsPage.sportOption(sport).click();
        assertThat(radioButtonsPage.sportOption(sport)).isChecked();
        radioButtonsPage.sportOption(sport).click();
        assertThat(radioButtonsPage.sportOption(sport)).isChecked();
        assertThat(radioButtonsPage.result).hasText("Color: blue, Sport: " + sport);
    }

    @Test
    void hockeyIsDisabledAndNotSelected() {
        assertThat(radioButtonsPage.hockey).isVisible();
        assertThat(radioButtonsPage.hockey).isDisabled();
        assertThat(radioButtonsPage.hockey).not().isChecked();
    }


}
