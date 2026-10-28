package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.EditorPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
@Tag("regression")
public class EditorTest extends BaseTest{
    private EditorPage editorPage;

    @BeforeEach
    void setupPage() {
        editorPage = new EditorPage(page);
        mainPage.navigateToEditor();
    }

    @Test
    @Tag("smoke")
    void editorDisplaysDisplayedContent() {
        assertThat(editorPage.header).hasText("An iFrame containing the TinyMCE WYSIWYG Editor");
        assertThat(editorPage.editor).isVisible();
        assertThat(editorPage.editor).isEditable();
        assertThat(editorPage.editor).hasText("Your content goes here.");
    }

    @Test
    void editorDisplaysMenusAndToolbarButtons() {
        assertThat(editorPage.fileMenu).isVisible();
        assertThat(editorPage.editMenu).isVisible();
        assertThat(editorPage.viewMenu).isVisible();
        assertThat(editorPage.insertMenu).isVisible();
        assertThat(editorPage.formatMenu).isVisible();

        assertThat(editorPage.undoBtn).isVisible();
        assertThat(editorPage.redoBtn).isVisible();
        assertThat(editorPage.paragraphFormatBtn).isVisible();
        assertThat(editorPage.boldBtn).isVisible();
        assertThat(editorPage.italicBtn).isVisible();
        assertThat(editorPage.alignLeftBtn).isVisible();
        assertThat(editorPage.alignCenterBtn).isVisible();
        assertThat(editorPage.alignRightBtn).isVisible();
        assertThat(editorPage.justifyBtn).isVisible();
        assertThat(editorPage.decreaseBtn).isVisible();
        assertThat(editorPage.increaseBtn).isVisible();
    }

    @Test
    void editorContentCanBeReplaced() {
        editorPage.replaceText("test123");
        assertThat(editorPage.editor).hasText("test123");
    }

    @Test
    void editorContentCanBeCleared() {
        editorPage.replaceText("test 123 test");
        assertThat(editorPage.editor).hasText("test 123 test");
        editorPage.editor.fill("");
        assertThat(editorPage.editor).hasText("");
    }

    @Test
    void selectedTextCanBeBold() {
        editorPage.replaceText("Bold 123");
        editorPage.selectAllText();
        editorPage.boldBtn.click();
        assertThat(editorPage.boldText).hasText("Bold 123");
        assertThat(editorPage.editor).hasText("Bold 123");
    }

    @Test
    void selectedTextCanBeItalic() {
        editorPage.replaceText("Italic 123");
        editorPage.selectAllText();
        editorPage.italicBtn.click();
        assertThat(editorPage.italicText).hasText("Italic 123");
        assertThat(editorPage.editor).hasText("Italic 123");
    }
    @Test
    void selectedTextCanBeBoldAndItalic() {
        editorPage.replaceText("test 123 test");
        editorPage.selectAllText();
        editorPage.boldBtn.click();
        editorPage.italicBtn.click();
        assertThat(editorPage.boldText).hasText("test 123 test");
        assertThat(editorPage.italicText).hasText("test 123 test");
    }

    @Test
    void textCanBeAlignedLeft() {
        editorPage.replaceText("test 123");
        editorPage.alignCenterBtn.click();
        assertThat(editorPage.paragraph).hasCSS("text-align", "center");
        editorPage.alignLeftBtn.click();
        assertThat(editorPage.paragraph).hasCSS("text-align", "left");
        assertThat(editorPage.editor).hasText("test 123");
    }

    @Test
    void textCanBeAlignedCenter() {
        editorPage.replaceText("test 123 test");
        editorPage.alignRightBtn.click();
        assertThat(editorPage.paragraph).hasCSS("text-align", "right");
        editorPage.alignCenterBtn.click();
        assertThat(editorPage.paragraph).hasCSS("text-align", "center");
        assertThat(editorPage.editor).hasText("test 123 test");
    }

    @Test
    void textCanBeAlignedRight() {
        editorPage.replaceText("test 123 test");
        editorPage.alignCenterBtn.click();
        assertThat(editorPage.paragraph).hasCSS("text-align", "center");
        editorPage.alignRightBtn.click();
        assertThat(editorPage.paragraph).hasCSS("text-align", "right");
        assertThat(editorPage.editor).hasText("test 123 test");
    }


    @Test
    void undoAndRedoRestoreTextFormatting() {
        editorPage.replaceText("test 123  test");
        editorPage.selectAllText();
        editorPage.boldBtn.click();
        assertThat(editorPage.boldText).hasText("test 123  test");
        editorPage.undoBtn.click();
        assertThat(editorPage.boldText).hasCount(0);
        assertThat(editorPage.editor).hasText("test 123  test");
        editorPage.redoBtn.click();
        assertThat(editorPage.boldText).hasText("test 123  test");
    }
}
