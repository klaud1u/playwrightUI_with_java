package tests;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.DragAndDropPage;

public class DragAndDropTest extends BaseTest{
    private DragAndDropPage dragAndDropPage;

    @BeforeEach
    void setupPage() {
        dragAndDropPage = new DragAndDropPage(page);
        mainPage.dragAndDropLink.click();
    }

    @Test
    public void NavigateToDragAndDropPage(){
        Assertions.assertEquals("Drag and Drop",dragAndDropPage.header.textContent().trim());
        Assertions.assertEquals("A", dragAndDropPage.columnA.textContent().trim());
        Assertions.assertEquals("B", dragAndDropPage.columnB.textContent().trim());
    }
    @Test
    void draggingColumnAToColumnBChangesTheirPositions() {
        Assertions.assertEquals("A", dragAndDropPage.columnA.textContent().trim());
        Assertions.assertEquals("B", dragAndDropPage.columnB.textContent().trim());
        dragAndDropPage.columnA.dragTo(dragAndDropPage.columnB);
        Assertions.assertEquals("B", dragAndDropPage.columnA.textContent().trim());
        Assertions.assertEquals("A", dragAndDropPage.columnB.textContent().trim());
    }

    @Test
    void draggingColumnBToColumnAChangesTheirPositions() {
        Assertions.assertEquals("A", dragAndDropPage.columnA.textContent().trim());
        Assertions.assertEquals("B", dragAndDropPage.columnB.textContent().trim());
        dragAndDropPage.columnB.dragTo(dragAndDropPage.columnA);
        Assertions.assertEquals("B", dragAndDropPage.columnA.textContent().trim());
        Assertions.assertEquals("A", dragAndDropPage.columnB.textContent().trim());
    }

    @Test
    void draggingColumnsTwiceTest() {
        Assertions.assertEquals("A", dragAndDropPage.columnA.textContent().trim());
        Assertions.assertEquals("B", dragAndDropPage.columnB.textContent().trim());
        dragAndDropPage.columnA.dragTo(dragAndDropPage.columnB);
        Assertions.assertEquals("B", dragAndDropPage.columnA.textContent().trim());
        Assertions.assertEquals("A", dragAndDropPage.columnB.textContent().trim());
        dragAndDropPage.columnA.dragTo(dragAndDropPage.columnB);
        Assertions.assertEquals("A", dragAndDropPage.columnA.textContent().trim());
        Assertions.assertEquals("B", dragAndDropPage.columnB.textContent().trim());
    }

    @Test
    void draggingColumnsOutsideDropTargetsDoesNotChangeTheirTexts() {
        Assertions.assertEquals("A", dragAndDropPage.columnA.textContent().trim());
        Assertions.assertEquals("B", dragAndDropPage.columnB.textContent().trim());
        dragAndDropPage.columnA.dragTo(dragAndDropPage.header);
        dragAndDropPage.columnB.dragTo(dragAndDropPage.header);
        Assertions.assertEquals("A", dragAndDropPage.columnA.textContent().trim());
        Assertions.assertEquals("B", dragAndDropPage.columnB.textContent().trim());
    }
}

