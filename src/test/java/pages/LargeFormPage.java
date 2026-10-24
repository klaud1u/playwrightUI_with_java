package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Mouse;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.BoundingBox;

public class LargeFormPage extends BasePage {
    public LargeFormPage(Page page) {
        super(page);
    }

    // Formular
    public Locator header = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Large Form").setExact(true));
    public Locator form = page.locator("#large-form");
    public Locator firstName = page.getByLabel("First name");
    public Locator lastName = page.getByLabel("Last name");
    public Locator email = page.locator("input[name='email']");
    public Locator password = page.locator("input[name='password']");
    public Locator nickname = page.getByPlaceholder("Nickname (optional)");
    public Locator birthday = page.getByLabel("Birthday");
    public Locator age = page.locator("input[name='age']");
    public Locator country = page.getByLabel("Country");
    public Locator languages = page.locator("select[name='languages[]']");
    public Locator aboutYou = page.getByLabel("About you");
    public Locator contactEmail = page.locator("input[name='contact'][value='email']");
    public Locator contactPhone = page.locator("input[name='contact'][value='phone']");
    public Locator dontContactMeBy = page.locator("input[name='contact'][value='none']");
    public Locator testingInterest = page.locator("input[name='interests[]'][value='testing']");
    public Locator automationInterest = page.locator("input[name='interests[]'][value='automation']");
    public Locator devopsInterest = page.locator("input[name='interests[]'][value='devops']");
    public Locator experience = page.getByLabel("Years of experience:");
    public Locator experienceValue = page.locator("#experience-value");
    public Locator color = page.locator("input[name='color']");
    public Locator terms = page.getByLabel("I accept the terms");
    public Locator submitButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit").setExact(true));
    public Locator resetButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Reset").setExact(true));

    //Submited Form
    public Locator submittedFormHeader = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Form Submitted").setExact(true));
    public Locator submittedFirstName = submittedValue("first_name");
    public Locator submittedLastName = submittedValue("last_name");
    public Locator submittedEmail = submittedValue("email");
    public Locator submittedPassword = submittedValue("password");
    public Locator submittedNickname = submittedValue("nickname");
    public Locator submittedBirthday = submittedValue("birthday");
    public Locator submittedAge = submittedValue("age");
    public Locator submittedCountry = submittedValue("country");
    public Locator submittedLanguages = submittedValue("languages");
    public Locator submittedAboutYou = submittedValue("bio");
    public Locator submittedContact = submittedValue("contact");
    public Locator submittedExperience = submittedValue("experience");
    public Locator submittedColor = submittedValue("color");
    public Locator submittedFormId = submittedValue("form_id");
    public Locator submittedInterests = submittedValue("interests");
    public Locator submittedTerms = submittedValue("terms");

    private Locator submittedValue(String fieldName) {
        return page.locator(
                "//table[@id='submitted-data']//tbody/tr" +
                        "[td[1][normalize-space()='" + fieldName + "']]/td[2]"
        );
    }


    public void dragExperienceTo(int targetValue) {
        int min = Integer.parseInt(experience.getAttribute("min"));
        int max = Integer.parseInt(experience.getAttribute("max"));
        BoundingBox box = experience.boundingBox();
        double thumbMargin = 8;
        double usableWidth = box.width - 2 * thumbMargin;
        double position = (double) (targetValue - min) / (max - min);
        double y = box.y + box.height / 2;
        double startX = box.x + thumbMargin;
        double targetX = box.x + thumbMargin + usableWidth * position;
        page.mouse().move(startX, y);
        page.mouse().down();
        page.mouse().move(targetX, y, new Mouse.MoveOptions().setSteps(10));
        page.mouse().up();
    }

    public Locator contactMeOption(String value) {
        return switch (value) {
            case "email" -> contactEmail;
            case "phone" -> contactPhone;
            case "none" -> dontContactMeBy;
            default -> throw new IllegalArgumentException("Unknown contact option: " + value);
        };

    }

    public Locator interestOption(String interest) {
        return switch (interest) {
            case "testing" -> testingInterest;
            case "automation" -> automationInterest;
            case "devops" -> devopsInterest;
            default -> throw new IllegalArgumentException(
                    "Unknown interest: " + interest
            );
        };
    }

    public void selectInterests(String... interests) {
        for (String interest : interests) {
            interestOption(interest).check();
        }
    }

    public void resizeAboutYouField(int widthIncrease, int heightIncrease) {
        aboutYou.scrollIntoViewIfNeeded();
        BoundingBox box = aboutYou.boundingBox();
        double x = box.x + box.width - 5;
        double y = box.y + box.height - 5;
        page.mouse().move(x, y);
        page.mouse().down();
        page.mouse().move(
                x + widthIncrease,
                y + heightIncrease,
                new Mouse.MoveOptions().setSteps(10)
        );
        page.mouse().up();
    }

}
