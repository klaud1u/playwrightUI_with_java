package tests;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DragAndDropTest extends BaseTest{
    @Test
    public void NavigateToDragAndDropPage(){
       mainPage.dragAndDropLink.click();
        Assertions.assertEquals("Drag and Drop",dragAndDropPage.header.textContent().trim());
        Assertions.assertEquals("A", dragAndDropPage.columnA.textContent().trim());
        Assertions.assertEquals("B", dragAndDropPage.columnB.textContent().trim());
    }
    @Test
    void draggingColumnAToColumnBChangesTheirPositions() {
        mainPage.dragAndDropLink.click();
        Assertions.assertEquals("A", dragAndDropPage.columnA.textContent().trim());
        Assertions.assertEquals("B", dragAndDropPage.columnB.textContent().trim());
        dragAndDropPage.columnA.dragTo(dragAndDropPage.columnB);
        Assertions.assertEquals("B", dragAndDropPage.columnA.textContent().trim());
        Assertions.assertEquals("A", dragAndDropPage.columnB.textContent().trim());
    }

    @Test
    void draggingColumnBToColumnAChangesTheirPositions() {
        mainPage.dragAndDropLink.click();
        Assertions.assertEquals("A", dragAndDropPage.columnA.textContent().trim());
        Assertions.assertEquals("B", dragAndDropPage.columnB.textContent().trim());
        dragAndDropPage.columnB.dragTo(dragAndDropPage.columnA);
        Assertions.assertEquals("B", dragAndDropPage.columnA.textContent().trim());
        Assertions.assertEquals("A", dragAndDropPage.columnB.textContent().trim());
    }

    @Test
    void draggingColumnsTwiceTest() {
        mainPage.dragAndDropLink.click();
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
        mainPage.dragAndDropLink.click();
        Assertions.assertEquals("A", dragAndDropPage.columnA.textContent().trim());
        Assertions.assertEquals("B", dragAndDropPage.columnB.textContent().trim());
        dragAndDropPage.columnA.dragTo(dragAndDropPage.header);
        dragAndDropPage.columnB.dragTo(dragAndDropPage.header);
        Assertions.assertEquals("A", dragAndDropPage.columnA.textContent().trim());
        Assertions.assertEquals("B", dragAndDropPage.columnB.textContent().trim());
    }
}

