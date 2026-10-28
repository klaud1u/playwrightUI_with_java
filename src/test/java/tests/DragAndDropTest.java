package tests;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import config.TestConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.DragAndDropPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
@Tag("regression")
public class DragAndDropTest extends BaseTest{
    private DragAndDropPage dragAndDropPage;

    @BeforeEach
    void setupPage() {
        dragAndDropPage = new DragAndDropPage(page);
        mainPage.navigateToDragAndDrop();
    }

    @Test
    @Tag("smoke")
    public void dragAndDropPageDisplaysExpectedContent(){
        assertThat(page).hasURL(TestConfig.BASE_URL + "/drag_and_drop");
        assertThat(dragAndDropPage.header).hasText("Drag and Drop");
        assertThat(dragAndDropPage.columnA).isVisible();
        assertThat(dragAndDropPage.columnA).hasText("A");
        assertThat(dragAndDropPage.columnB).isVisible();
        assertThat(dragAndDropPage.columnB).hasText("B");
    }
    @Test
    void draggingColumnAToColumnBChangesTheirPositions() {
        assertThat(dragAndDropPage.columnA).hasText("A");
        assertThat(dragAndDropPage.columnB).hasText("B");
        dragAndDropPage.columnA.dragTo(dragAndDropPage.columnB);
        assertThat(dragAndDropPage.columnA).hasText("B");
        assertThat(dragAndDropPage.columnB).hasText("A");
    }

    @Test
    void draggingColumnBToColumnAChangesTheirPositions() {
        assertThat(dragAndDropPage.columnA).hasText("A");
        assertThat(dragAndDropPage.columnB).hasText("B");
        dragAndDropPage.columnB.dragTo(dragAndDropPage.columnA);
        assertThat(dragAndDropPage.columnA).hasText("B");
        assertThat(dragAndDropPage.columnB).hasText("A");
    }

    @Test
    void draggingColumnsTwiceTest() {
        assertThat(dragAndDropPage.columnA).hasText("A");
        assertThat(dragAndDropPage.columnB).hasText("B");
        dragAndDropPage.columnA.dragTo(dragAndDropPage.columnB);
        assertThat(dragAndDropPage.columnA).hasText("B");
        assertThat(dragAndDropPage.columnB).hasText("A");
        dragAndDropPage.columnA.dragTo(dragAndDropPage.columnB);
        assertThat(dragAndDropPage.columnA).hasText("A");
        assertThat(dragAndDropPage.columnB).hasText("B");
    }

    @Test
    void draggingColumnsOutsideDropTargetsDoesNotChangeTheirTexts() {
        assertThat(dragAndDropPage.columnA).hasText("A");
        assertThat(dragAndDropPage.columnB).hasText("B");
        dragAndDropPage.columnA.dragTo(dragAndDropPage.header);
        dragAndDropPage.columnB.dragTo(dragAndDropPage.header);
        assertThat(dragAndDropPage.columnA).hasText("A");
        assertThat(dragAndDropPage.columnB).hasText("B");
    }
}

