package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.HoversPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
@Tag("regression")
public class HoversTest extends BaseTest{
    private HoversPage hoversPage;

    @BeforeEach
    void setupPage() {
        hoversPage = new HoversPage(page);
        mainPage.navigateToHovers();
    }


    @Test
    @Tag("smoke")
    void hoverOverUser1ShowsItsProfile() {
        assertThat(hoversPage.userName(0)).isHidden();
        assertThat(hoversPage.userProfileLink(0)).isHidden();
        hoversPage.userImage(0).hover();
        assertThat(hoversPage.userName(0)).hasText("name: user1");
        assertThat(hoversPage.userProfileLink(0)).isVisible();
    }

    @Test
    void hoverOverUser2ShowsItsProfile() {
        assertThat(hoversPage.userName(1)).isHidden();
        assertThat(hoversPage.userProfileLink(1)).isHidden();
        hoversPage.userImage(1).hover();
        assertThat(hoversPage.userName(1)).hasText("name: user2");
        assertThat(hoversPage.userProfileLink(1)).isVisible();
    }

    @Test
    void hoverOverUser3ShowsItsProfile() {
        assertThat(hoversPage.userName(2)).isHidden();
        assertThat(hoversPage.userProfileLink(2)).isHidden();
        hoversPage.userImage(2).hover();
        assertThat(hoversPage.userName(2)).hasText("name: user3");
        assertThat(hoversPage.userProfileLink(2)).isVisible();
    }

    @Test
    void movingHoverFromUser1ToUser3UpdatesProfile() {
        hoversPage.userImage(0).hover();
        assertThat(hoversPage.userName(0)).hasText("name: user1");
        assertThat(hoversPage.userProfileLink(0)).isVisible();
        hoversPage.userImage(2).hover();
        assertThat(hoversPage.userName(0)).isHidden();
        assertThat(hoversPage.userProfileLink(0)).isHidden();
        assertThat(hoversPage.userName(2)).hasText("name: user3");
        assertThat(hoversPage.userProfileLink(2)).isVisible();
    }
}
