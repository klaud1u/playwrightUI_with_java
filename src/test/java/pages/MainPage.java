package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class MainPage extends BasePage {
    public MainPage(Page page) {
        super(page);
    }

    public Locator AbTestingLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("A/B Testing").setExact(true));
    public Locator addRemoveElements = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Add/Remove Elements").setExact(true));
    public Locator brokenImagesLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Broken Images").setExact(true));
    public Locator basicAuthLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Basic Auth").setExact(true));
    public Locator challengingDOMLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Challenging DOM").setExact(true));
    public Locator checkboxLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Checkboxes").setExact(true));
    public Locator digestAuthenticationLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Digest Authentication").setExact(true));
    public Locator contextMenuLink= page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Context Menu").setExact(true));
    public Locator disappearingElementsLink= page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Disappearing Elements").setExact(true));
    public Locator dragAndDropLink = page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Drag and Drop").setExact(true));
    public Locator dropdownLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Dropdown").setExact(true));
    public Locator dynamicContentLink = page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Dynamic Content"));
    public Locator fileDownloadLink = page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("File Download").setExact(true));
    public Locator fileUploadLink = page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("File Upload").setExact(true));
    public Locator floatingMenuLink = page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Floating Menu").setExact(true));
    public Locator forgotPasswordLink =page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Forgot Password").setExact(true));
    public Locator formAuthenticationLink =page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Form Authentication").setExact(true));
    public Locator framesLink =page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Frames").setExact(true));
    public Locator geolocationLink =page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Geolocation").setExact(true));
    public Locator horizontalSliderLink =page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Horizontal Slider").setExact(true));
    public Locator hoversLink =page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Hovers").setExact(true));
    public Locator infiniteScrollLink =page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Infinite Scroll").setExact(true));
    public Locator inputsLink =page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Inputs").setExact(true));
    public Locator jqueryUiMenusLink =page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("JQuery UI Menus").setExact(true));
    public Locator keyPressesLink =page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Key Presses").setExact(true));
    public Locator javaScriptAlertsLink =page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("JavaScript Alerts").setExact(true));
    public Locator largeAndDeepDOMLink =page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Large & Deep DOM").setExact(true));
    public Locator largeFormLink =page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Large Form").setExact(true));
    public Locator multipleSelectDropdownLink =page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Multiple Select Dropdown").setExact(true));
    public Locator multipleWindowsLink =page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Multiple Windows").setExact(true));
    public Locator nestedFramesLink =page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Nested Frames").setExact(true));
    public Locator radioButtonsLink =page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Radio Buttons").setExact(true));
    public Locator readOnlyInputsLink =page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Read-only Inputs").setExact(true));
    public Locator secureFileDownloadLink =page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Secure File Download").setExact(true));
    public Locator shadowDOMLink = page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Shadow DOM").setExact(true));
    public Locator sortableDataTablesLink = page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Sortable Data Tables").setExact(true));
    public Locator statusCodesLink = page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Status Codes").setExact(true));
    public Locator subdirectoriesLink = page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Subdirectories").setExact(true));
    public Locator twoFactorLoginLink = page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Two-Factor Login").setExact(true));
    public Locator typosLink = page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Typos").setExact(true));
    public Locator editorPageLink = page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("WYSIWYG Editor").setExact(true));
    public Locator canvasLink = page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Canvas").setExact(true));
    public Locator exitIntentLink = page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Exit Intent").setExact(true));
    public Locator entryAdLink = page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Entry Ad").setExact(true));
    public Locator dynamicLoadingLink = page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Dynamic Loading").setExact(true));
    public Locator dynamicControlLink = page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Dynamic Controls").setExact(true));
    public Locator shiftingContentLink = page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Shifting Content").setExact(true));
    public Locator redirectLink = page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Redirect Link").setExact(true));
    public Locator javaScriptOnloadEventErrorLink = page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("JavaScript onload event error").setExact(true));

    public void navigateToABTesting() {
        AbTestingLink.click();
    }

    public void navigateToAddRemoveElements() {
        addRemoveElements.click();
    }

    public void navigateToBasicAuth() {
        basicAuthLink.click();
    }

    public void navigateToBrokenImages() {
        brokenImagesLink.click();
    }
    public void navigateToCanvas() {
        canvasLink.click();
    }

    public void navigateToChallengingDOM() {
        challengingDOMLink.click();
    }

    public void navigateToCheckboxes() {
        checkboxLink.click();
    }

    public void navigateToContextMenu() {
        contextMenuLink.click();
    }

    public void navigateToDigestAuthentication() {
        digestAuthenticationLink.click();
    }

    public void navigateToDisappearingElements() {
        disappearingElementsLink.click();
    }

    public void navigateToDragAndDrop() {
        dragAndDropLink.click();
    }

    public void navigateToDropdown() {
        dropdownLink.click();
    }

    public void navigateToDynamicContent() {
        dynamicContentLink.click();
    }

    public void navigateToDynamicControls() {
        dynamicControlLink.click();
    }

    public void navigateToDynamicLoading() {
        dynamicLoadingLink.click();
    }

    public void navigateToEditor() {
        editorPageLink.click();
    }

    public void navigateToEntryAd() {
        entryAdLink.click();
    }

    public void navigateToExitIntent() {
        exitIntentLink.click();
    }

    public void navigateToFileDownload() {
        fileDownloadLink.click();
    }

    public void navigateToFileUpload() {
        fileUploadLink.click();
    }

    public void navigateToFloatingMenu() {
        floatingMenuLink.click();
    }

    public void navigateToForgotPassword() {
        forgotPasswordLink.click();
    }

    public void navigateToFormAuthentication() {
        formAuthenticationLink.click();
    }

    public void navigateToFrames() {
        framesLink.click();
    }

    public void navigateToGeolocation() {
        geolocationLink.click();
    }

    public void navigateToHorizontalSlider() {
        horizontalSliderLink.click();
    }

    public void navigateToHovers() {
        hoversLink.click();
    }

    public void navigateToInfiniteScroll() {
        infiniteScrollLink.click();
    }

    public void navigateToInputs() {
        inputsLink.click();
    }

    public void navigateToJavaScriptAlerts() {
        javaScriptAlertsLink.click();
    }

    public void navigateToJavaScriptOnloadEventError() {
        javaScriptOnloadEventErrorLink.click();
    }

    public void navigateToJqueryUIMenus() {
        jqueryUiMenusLink.click();
    }

    public void navigateToKeyPresses() {
        keyPressesLink.click();
    }

    public void navigateToLargeAndDeepDOM() {
        largeAndDeepDOMLink.click();
    }

    public void navigateToLargeForm() {
        largeFormLink.click();
    }

    public void navigateToMultipleSelectDropdown() {
        multipleSelectDropdownLink.click();
    }

    public void navigateToMultipleWindows() {
        multipleWindowsLink.click();
    }

    public void navigateToNestedFrames() {
        nestedFramesLink.click();
    }

    public void navigateToRadioButtons() {
        radioButtonsLink.click();
    }

    public void navigateToReadOnlyInputs() {
        readOnlyInputsLink.click();
    }

    public void navigateToRedirection() {
        redirectLink.click();
    }

    public void navigateToSecureFileDownload() {
        secureFileDownloadLink.click();
    }

    public void navigateToShadowDOM() {
        shadowDOMLink.click();
    }

    public void navigateToShiftingContent() {
        shiftingContentLink.click();
    }

    public void navigateToSortableDataTables() {
        sortableDataTablesLink.click();
    }

    public void navigateToStatusCodes() {
        statusCodesLink.click();
    }

    public void navigateToSubdirectories() {
        subdirectoriesLink.click();
    }

    public void navigateToTwoFactorLogin() {
        twoFactorLoginLink.click();
    }

    public void navigateToTypos() {
        typosLink.click();
    }

}