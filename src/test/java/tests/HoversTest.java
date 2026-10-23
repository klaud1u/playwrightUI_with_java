package tests;

import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class HoversTest extends BaseTest{


    @Test
    void hoverOverUser1ShowsItsProfile() {
        mainPage.hoversLink.click();
        assertThat(hoversPage.userName(0)).isHidden();
        assertThat(hoversPage.userProfileLink(0)).isHidden();
        hoversPage.userImage(0).hover();
        assertThat(hoversPage.userName(0)).hasText("name: user1");
        assertThat(hoversPage.userProfileLink(0)).isVisible();
    }

    @Test
    void hoverOverUser2ShowsItsProfile() {
        mainPage.hoversLink.click();
        assertThat(hoversPage.userName(1)).isHidden();
        assertThat(hoversPage.userProfileLink(1)).isHidden();
        hoversPage.userImage(1).hover();
        assertThat(hoversPage.userName(1)).hasText("name: user2");
        assertThat(hoversPage.userProfileLink(1)).isVisible();
    }

    @Test
    void hoverOverUser3ShowsItsProfile() {
        mainPage.hoversLink.click();
        assertThat(hoversPage.userName(2)).isHidden();
        assertThat(hoversPage.userProfileLink(2)).isHidden();
        hoversPage.userImage(2).hover();
        assertThat(hoversPage.userName(2)).hasText("name: user3");
        assertThat(hoversPage.userProfileLink(2)).isVisible();
    }

    @Test
    void movingHoverFromUser1ToUser3UpdatesProfile() {
        mainPage.hoversLink.click();
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
