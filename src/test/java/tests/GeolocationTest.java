package tests;

import com.microsoft.playwright.options.Geolocation;
import config.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.GeolocationPage;

import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
@Tag("regression")
public class GeolocationTest extends BaseTest{

    private GeolocationPage geolocationPage;

    @BeforeEach
    void setupPage() {
        geolocationPage = new GeolocationPage(page);
        context.grantPermissions(List.of("geolocation"));
        mainPage.navigateToGeolocation();
    }

    @Test
    @Tag("smoke")
    void weAreOnGeolocationPage() {
        assertThat(page).hasURL(TestConfig.BASE_URL + "/geolocation");
        assertThat(geolocationPage.header).hasText("Geolocation");
        assertThat(geolocationPage.locationMessage).hasText("Click the button to get your current latitude and longitude");
        assertThat(geolocationPage.whereAmIBtn).isVisible();
        assertThat(geolocationPage.latitude).hasCount(0);
        assertThat(geolocationPage.longitude).hasCount(0);
        assertThat(geolocationPage.googleMapsLink).hasCount(0);
    }

    @Test
    void clickingButtonDisplaysLocation() {
        context.setGeolocation(new Geolocation(44.4268, 26.1025));
        geolocationPage.whereAmIBtn.click();
        assertThat(geolocationPage.latitude).hasText("44.4268");
        assertThat(geolocationPage.longitude).hasText("26.1025");
        assertThat(geolocationPage.googleMapsLink).isVisible();
        assertThat(geolocationPage.googleMapsLink).hasAttribute("href", "http://maps.google.com/?q=44.4268,26.1025");
    }

    @Test
    void clickingAgainDisplaysUpdatedLocation() {
        context.setGeolocation(new Geolocation(44.4268, 26.1025));
        geolocationPage.whereAmIBtn.click();
        assertThat(geolocationPage.latitude).hasText("44.4268");
        assertThat(geolocationPage.longitude).hasText("26.1025");
        context.setGeolocation(new Geolocation(48.8566, 2.3522));
        geolocationPage.whereAmIBtn.click();
        assertThat(geolocationPage.latitude).hasText("48.8566");
        assertThat(geolocationPage.longitude).hasText("2.3522");
        assertThat(geolocationPage.googleMapsLink).hasAttribute("href", "http://maps.google.com/?q=48.8566,2.3522"
        );
    }
}
