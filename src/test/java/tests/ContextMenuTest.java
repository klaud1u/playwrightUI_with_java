package tests;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.MouseButton;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.ContextMenuPage;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class ContextMenuTest extends BaseTest {
    private ContextMenuPage contextMenuPage;

    @BeforeEach
    void setupPage() {
        contextMenuPage = new ContextMenuPage(page);
        mainPage.contextMenuLink.click();
    }

    @Test
    void navigateToContextMenuPage() {

        Assertions.assertEquals("Context Menu", contextMenuPage.header.textContent().trim());
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
