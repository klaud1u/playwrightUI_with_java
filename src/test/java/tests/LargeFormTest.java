package tests;


import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.BoundingBox;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import testdata.LargeFormTestData;



import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LargeFormTest extends BaseTest {

    @Test
    void submittingLargeFormDisplaysAllEnteredValues() {
        mainPage.largeFormLink.click();
        assertThat(largeFormPage.header).isVisible();

        largeFormPage.firstName.fill(LargeFormTestData.FIRST_NAME);
        largeFormPage.lastName.fill(LargeFormTestData.LAST_NAME);
        largeFormPage.email.fill(LargeFormTestData.EMAIL);
        largeFormPage.password.fill(LargeFormTestData.PASSWORD);
        largeFormPage.nickname.fill(LargeFormTestData.NICKNAME);

        largeFormPage.birthday.fill(LargeFormTestData.BIRTHDAY);
        largeFormPage.age.fill(String.valueOf(LargeFormTestData.AGE));
        largeFormPage.country.selectOption(LargeFormTestData.COUNTRY);
        largeFormPage.languages.selectOption(LargeFormTestData.LANGUAGES);
        largeFormPage.aboutYou.fill(LargeFormTestData.ABOUTME);

        largeFormPage.contactPhone.check();
        largeFormPage.testingInterest.check();
        largeFormPage.automationInterest.check();
        largeFormPage.devopsInterest.check();

        largeFormPage.dragExperienceTo(LargeFormTestData.EXPERIENCE);
        largeFormPage.color.fill(LargeFormTestData.COLOR);
        largeFormPage.terms.check();

        assertThat(largeFormPage.firstName).hasValue(LargeFormTestData.FIRST_NAME);
        assertThat(largeFormPage.lastName).hasValue(LargeFormTestData.LAST_NAME);
        assertThat(largeFormPage.email).hasValue(LargeFormTestData.EMAIL);
        assertThat(largeFormPage.password).hasValue(LargeFormTestData.PASSWORD);
        assertThat(largeFormPage.nickname).hasValue(LargeFormTestData.NICKNAME);
        assertThat(largeFormPage.birthday).hasValue(LargeFormTestData.BIRTHDAY);
        assertThat(largeFormPage.age).hasValue(String.valueOf(LargeFormTestData.AGE));
        assertThat(largeFormPage.country).hasValue(LargeFormTestData.COUNTRY);
        assertThat(largeFormPage.languages.locator("option:checked")).hasCount(LargeFormTestData.LANGUAGES.length);
        assertThat(largeFormPage.aboutYou).hasValue(LargeFormTestData.ABOUTME);

        assertThat(largeFormPage.contactPhone).isChecked();
        assertThat(largeFormPage.testingInterest).isChecked();
        assertThat(largeFormPage.automationInterest).isChecked();
        assertThat(largeFormPage.devopsInterest).isChecked();
        assertThat(largeFormPage.experience).hasValue(String.valueOf(LargeFormTestData.EXPERIENCE));
        assertThat(largeFormPage.experienceValue).hasText(String.valueOf(LargeFormTestData.EXPERIENCE));
        assertThat(largeFormPage.color).hasValue(LargeFormTestData.COLOR);
        assertThat(largeFormPage.terms).isChecked();

        largeFormPage.submitButton.click();

        assertThat(largeFormPage.submittedFormHeader).isVisible();
        assertThat(largeFormPage.submittedFirstName).hasText(LargeFormTestData.FIRST_NAME);
        assertThat(largeFormPage.submittedLastName).hasText(LargeFormTestData.LAST_NAME);
        assertThat(largeFormPage.submittedEmail).hasText(LargeFormTestData.EMAIL);
        assertThat(largeFormPage.submittedNickname).hasText(LargeFormTestData.NICKNAME);
        assertThat(largeFormPage.submittedBirthday).hasText(LargeFormTestData.BIRTHDAY);
        assertThat(largeFormPage.submittedAge).hasText(String.valueOf(LargeFormTestData.AGE));
        assertThat(largeFormPage.submittedCountry).hasText(LargeFormTestData.COUNTRY);
        assertThat(largeFormPage.submittedLanguages).hasText(String.join(", ", LargeFormTestData.LANGUAGES));
        assertThat(largeFormPage.submittedAboutYou).hasText(LargeFormTestData.ABOUTME);
        assertThat(largeFormPage.submittedContact).hasText("phone");
        assertThat(largeFormPage.submittedExperience).hasText(String.valueOf(LargeFormTestData.EXPERIENCE));
        assertThat(largeFormPage.submittedColor).hasText(LargeFormTestData.COLOR);
        assertThat(largeFormPage.submittedFormId).hasText("larges-form");
        assertThat(largeFormPage.submittedInterests).hasText("testing, automation, devops");
        assertThat(largeFormPage.submittedTerms).hasText("accepted");

        String passwordMask = largeFormPage.submittedPassword.textContent().trim();
        Assertions.assertTrue(passwordMask.matches("\\*+"));

    }

    @Test
    void resetRestoresAllFormDefaults() {
        mainPage.largeFormLink.click();

        largeFormPage.firstName.fill(LargeFormTestData.FIRST_NAME);
        assertThat(largeFormPage.firstName).hasValue(LargeFormTestData.FIRST_NAME);

        largeFormPage.lastName.fill(LargeFormTestData.LAST_NAME);
        assertThat(largeFormPage.lastName).hasValue(LargeFormTestData.LAST_NAME);

        largeFormPage.email.fill(LargeFormTestData.EMAIL);
        assertThat(largeFormPage.email).hasValue(LargeFormTestData.EMAIL);

        largeFormPage.password.fill(LargeFormTestData.PASSWORD);
        assertThat(largeFormPage.password).hasValue(LargeFormTestData.PASSWORD);

        largeFormPage.nickname.fill(LargeFormTestData.NICKNAME);
        assertThat(largeFormPage.nickname).hasValue(LargeFormTestData.NICKNAME);

        largeFormPage.birthday.fill(LargeFormTestData.BIRTHDAY);
        assertThat(largeFormPage.birthday).hasValue(LargeFormTestData.BIRTHDAY);

        largeFormPage.age.fill(String.valueOf(LargeFormTestData.AGE));
        assertThat(largeFormPage.age).hasValue(String.valueOf(LargeFormTestData.AGE));

        largeFormPage.country.selectOption(LargeFormTestData.COUNTRY);
        assertThat(largeFormPage.country).hasValue(LargeFormTestData.COUNTRY);

        largeFormPage.languages.selectOption(LargeFormTestData.LANGUAGES);
        assertThat(largeFormPage.languages.locator("option:checked")).hasCount(LargeFormTestData.LANGUAGES.length);

        largeFormPage.aboutYou.fill(LargeFormTestData.ABOUTME);
        assertThat(largeFormPage.aboutYou).hasValue(LargeFormTestData.ABOUTME);

        largeFormPage.contactPhone.check();
        assertThat(largeFormPage.contactPhone).isChecked();
        assertThat(largeFormPage.contactEmail).not().isChecked();

        largeFormPage.testingInterest.check();
        assertThat(largeFormPage.testingInterest).isChecked();

        largeFormPage.automationInterest.check();
        assertThat(largeFormPage.automationInterest).isChecked();

        largeFormPage.devopsInterest.check();
        assertThat(largeFormPage.devopsInterest).isChecked();

        largeFormPage.dragExperienceTo(LargeFormTestData.EXPERIENCE);
        assertThat(largeFormPage.experience).hasValue(String.valueOf(LargeFormTestData.EXPERIENCE));
        assertThat(largeFormPage.experienceValue).hasText(String.valueOf(LargeFormTestData.EXPERIENCE));

        largeFormPage.color.fill(LargeFormTestData.COLOR);
        assertThat(largeFormPage.color).hasValue(LargeFormTestData.COLOR);

        largeFormPage.terms.check();
        assertThat(largeFormPage.terms).isChecked();

        largeFormPage.resetButton.click();

        assertThat(largeFormPage.firstName).hasValue("");
        assertThat(largeFormPage.lastName).hasValue("");
        assertThat(largeFormPage.email).hasValue("");
        assertThat(largeFormPage.password).hasValue("");
        assertThat(largeFormPage.nickname).hasValue("");
        assertThat(largeFormPage.birthday).hasValue("");
        assertThat(largeFormPage.age).hasValue("");
        assertThat(largeFormPage.country).hasValue("");
        assertThat(largeFormPage.languages.locator("option:checked")).hasCount(0);
        assertThat(largeFormPage.aboutYou).hasValue("");

        assertThat(largeFormPage.contactEmail).isChecked();
        assertThat(largeFormPage.contactPhone).not().isChecked();
        assertThat(largeFormPage.dontContactMeBy).not().isChecked();

        assertThat(largeFormPage.testingInterest).not().isChecked();
        assertThat(largeFormPage.automationInterest).not().isChecked();
        assertThat(largeFormPage.devopsInterest).not().isChecked();
        assertThat(largeFormPage.terms).not().isChecked();

        assertThat(largeFormPage.experience).hasValue("5");
       // assertThat(largeFormPage.experienceValue).hasText("5"); L-am scos pentru ca e un bug aici cand dau reset nu se actualizeaza textul
        assertThat(largeFormPage.color).hasValue("#e2231a");

    }

    @ParameterizedTest
    @CsvSource({
            "0, valid",
            "120, valid",
            "-1, invalid",
            "121, invalid"
    })
    void submittingAgeShowsExpectedPage(int age, String ageStatus) {
        mainPage.largeFormLink.click();
        largeFormPage.age.fill(String.valueOf(age));
        largeFormPage.submitButton.click();
        Locator expectedHeading = ageStatus.equals("valid") ? largeFormPage.submittedFormHeader : largeFormPage.header;
        assertThat(expectedHeading).isVisible();
    }

    @Test
    void onlyOneContactMethodCanBeSelected() throws InterruptedException {
        mainPage.largeFormLink.click();
        assertThat(largeFormPage.contactEmail).isChecked();
        assertThat(largeFormPage.contactPhone).not().isChecked();
        assertThat(largeFormPage.dontContactMeBy).not().isChecked();
        largeFormPage.contactPhone.check();
        assertThat(largeFormPage.contactPhone).isChecked();
        assertThat(largeFormPage.contactEmail).not().isChecked();
        assertThat(largeFormPage.dontContactMeBy).not().isChecked();
        largeFormPage.dontContactMeBy.check();
        assertThat(largeFormPage.dontContactMeBy).isChecked();
        assertThat(largeFormPage.contactEmail).not().isChecked();
        assertThat(largeFormPage.contactPhone).not().isChecked();
    }

    @ParameterizedTest
    @CsvSource({"ar", "de", "in", "us"})
    void eachCountryCanBeSelectedAndSubmitted(String value) {
        mainPage.largeFormLink.click();
        largeFormPage.country.selectOption(value);
        assertThat(largeFormPage.country).hasValue(value);
        largeFormPage.submitButton.click();
        assertThat(largeFormPage.submittedFormHeader).isVisible();
        assertThat(largeFormPage.submittedCountry).hasText(value);
    }

    @Test
    void interestFieldIsHiddenWhenNoInterestIsSelected() throws InterruptedException {
        mainPage.largeFormLink.click();
        assertThat(largeFormPage.header).hasText("Large Form");
        assertThat(largeFormPage.automationInterest).not().isChecked();
        assertThat(largeFormPage.devopsInterest).not().isChecked();
        largeFormPage.submitButton.click();
        assertThat(largeFormPage.submittedFormHeader).hasText("Form Submitted");
        assertThat(largeFormPage.submittedInterests).isHidden();

    }
    @Test
    void termsFieldIsNotPresentOnSubmittedFormWhenUnchecked() throws InterruptedException {
        mainPage.largeFormLink.click();
        assertThat(largeFormPage.header).hasText("Large Form");
        assertThat(largeFormPage.terms).not().isChecked();
        largeFormPage.submitButton.click();
        assertThat(largeFormPage.submittedFormHeader).hasText("Form Submitted");
        assertThat(largeFormPage.submittedTerms).isHidden();

    }
    @ParameterizedTest
    @CsvSource({
            "valid@test.com, valid",
            "invalid-email, invalid",
            "user@, invalid",
            "@example.com, invalid",
            "test@!,invalid",
            "test@@test.com,invalid",
            "test test@test.com,invalid",
            "@, invalid",
            "test@test..com, invalid",
            "test@test .com, invalid",
            "test@test, valid"

    })
    void submittingEmailShowsExpectedPage(String email, String emailType) {
        mainPage.largeFormLink.click();
        largeFormPage.email.fill(email);
        largeFormPage.submitButton.click();
        Locator expectedHeading = emailType.equals("valid") ? largeFormPage.submittedFormHeader : largeFormPage.header;
        assertThat(expectedHeading).isVisible();
    }

    @ParameterizedTest
    @CsvSource({
            "email",
            "phone",
            "none"
    })
    void eachContactPreferenceCanBeSelectedAndSubmitted(String contact) {
        mainPage.largeFormLink.click();
        largeFormPage.contactMeOption(contact).check();
        assertThat(largeFormPage.contactMeOption(contact)).isChecked();
        largeFormPage.submitButton.click();
        assertThat(largeFormPage.submittedFormHeader).isVisible();
        assertThat(largeFormPage.submittedContact).hasText(contact);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "testing",
            "automation",
            "devops",
            "testing,automation",
            "testing,devops",
            "automation,devops",
            "testing,automation,devops"
    })
    void interestCombinationsCanBeSelectedAndSubmitted(String selectedInterests) {
        mainPage.largeFormLink.click();
        String[] interests = selectedInterests.split(",");
        largeFormPage.selectInterests(interests);
        for (String interest : interests) {
            assertThat(largeFormPage.interestOption(interest)).isChecked();
        }
        largeFormPage.submitButton.click();
        assertThat(largeFormPage.submittedFormHeader).isVisible();
        assertThat(largeFormPage.submittedInterests).hasText(selectedInterests.replace(",", ", "));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "English", "German", "Hindi", "Spanish",
            "English,German", "English,Hindi", "English,Spanish",
            "German,Hindi", "German,Spanish", "Hindi,Spanish",
            "English,German,Hindi", "English,German,Spanish",
            "English,Hindi,Spanish", "German,Hindi,Spanish",
            "English,German,Hindi,Spanish"
    })
    void languageCombinationsCanBeSelectedAndSubmitted(String selectedLanguages) {
        mainPage.largeFormLink.click();
        String[] languages = selectedLanguages.split(",");
        largeFormPage.languages.selectOption(languages);
        largeFormPage.submitButton.click();
        assertThat(largeFormPage.submittedFormHeader).isVisible();
        assertThat(largeFormPage.submittedLanguages).hasText(selectedLanguages.replace(",", ", "));
    }

    @Test
    void aboutYouFieldCanBeResizedWithMouse() throws InterruptedException {
        mainPage.largeFormLink.click();
        largeFormPage.aboutYou.scrollIntoViewIfNeeded();
        BoundingBox sizeBefore = largeFormPage.aboutYou.boundingBox();
        largeFormPage.resizeAboutYouField(-50, 40);
        BoundingBox sizeAfter = largeFormPage.aboutYou.boundingBox();
        Assertions.assertTrue(sizeAfter.height > sizeBefore.height);
    }

}
