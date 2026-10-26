package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class EditorPage extends BasePage {
    public EditorPage(Page page){
        super(page);
    }

    public Locator header = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("An iFrame containing the TinyMCE WYSIWYG Editor").setExact(true));

    // btn
    public Locator undoBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Undo").setExact(true));
    public Locator redoBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Redo").setExact(true));
    public Locator paragraphFormatBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Format Paragraph").setExact(true));
    public Locator boldBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Bold").setExact(true));
    public Locator italicBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Italic").setExact(true));
    public Locator alignLeftBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Align left").setExact(true));
    public Locator alignCenterBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Align center").setExact(true));
    public Locator alignRightBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Align right").setExact(true));
    public Locator justifyBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Justify").setExact(true));
    public Locator decreaseBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Decrease indent").setExact(true));
    public Locator increaseBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Increase indent").setExact(true));

    // menu
    public Locator fileMenu = page.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName("File").setExact(true));
    public Locator editMenu = page.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName("Edit").setExact(true));
    public Locator viewMenu = page.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName("View").setExact(true));
    public Locator insertMenu = page.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName("Insert").setExact(true));
    public Locator formatMenu = page.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName("Format").setExact(true));

    //iframe
    public Locator editor = page.frameLocator("iframe[title='Rich Text Area']").locator("body");
    public Locator paragraph = editor.locator("p");
    public Locator boldText = editor.locator("strong");
    public Locator italicText = editor.locator("em");


    public void replaceText(String text) {
        editor.click();
        editor.press("ControlOrMeta+A");
        editor.fill(text);
    }

    public void selectAllText() {
        editor.click();
        editor.press("ControlOrMeta+A");
    }
}

