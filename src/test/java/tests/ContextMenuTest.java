package tests;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.MouseButton;
import config.TestConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.ContextMenuPage;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
@Tag("regression")
public class ContextMenuTest extends BaseTest {
    private ContextMenuPage contextMenuPage;

    @BeforeEach
    void setupPage() {
        contextMenuPage = new ContextMenuPage(page);
        mainPage.navigateToContextMenu();
    }

    @Test
    @Tag("smoke")
    void navigateToContextMenuPage() {
        assertThat(page).hasURL(TestConfig.BASE_URL + "/context_menu");
        assertThat(contextMenuPage.header).hasText("Context Menu");
        assertThat(contextMenuPage.box).isVisible();
    }
    @Test
    void rightClickOpenDialog() {
        AtomicReference<String> dialogMessage = new AtomicReference<>();
        page.onceDialog(dialog -> {dialogMessage.set(dialog.message());System.out.println("Mesaj: " + dialog.message());dialog.accept();});
        contextMenuPage.box.click(new Locator.ClickOptions().setButton(MouseButton.RIGHT));
        Assertions.assertEquals("You selected a context menu", dialogMessage.get()
        );
    }
    @Test
    void leftClickDoesNotOpenDialog() {
        AtomicBoolean dialogAppeared = new AtomicBoolean(false);
        page.onDialog(dialog -> {dialogAppeared.set(true);dialog.dismiss();});
        contextMenuPage.box.click();
        Assertions.assertFalse(dialogAppeared.get());
    }
}
