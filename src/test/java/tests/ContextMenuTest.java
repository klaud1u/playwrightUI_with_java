package tests;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.MouseButton;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class ContextMenuTest extends BaseTest {

    @Test
    void navigateToContextMenuPage() {
        mainPage.contextMenuLink.click();
        Assertions.assertEquals("Context Menu", contextMenuPage.header.textContent().trim());
    }
    @Test
    void rightClickOpenDialog() {
        mainPage.contextMenuLink.click();
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
        mainPage.contextMenuLink.click();
        contextMenuPage.box.click();
        Assertions.assertFalse(dialogAppeared.get());
    }
}
