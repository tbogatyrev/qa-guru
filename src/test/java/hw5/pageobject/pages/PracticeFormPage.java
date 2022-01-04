package hw5.pageobject.pages;

import com.codeborne.selenide.SelenideElement;
import hw5.pageobject.pages.components.CalendarComponent;
import hw5.pageobject.pages.models.BirthDate;
import hw5.pageobject.pages.models.StudentModel;
import hw5.pageobject.pages.models.SubmittingFormModel;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static hw5.pageobject.utils.Utils.convertResponseToModel;
import static hw5.pageobject.utils.Utils.getBrowserResponseExecutingJs;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PracticeFormPage {

    private final String practiceFormPage = "/automation-practice-form";
    private StudentModel studentModel;

    private final SelenideElement
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            email = $("#userEmail"),
            phoneNumber = $("#userNumber"),
            studentGender = $("#genterWrapper"),
            studentSubjects = $("#subjectsInput"),
            studentHobbies = $("#hobbiesWrapper"),
            uploadPicture = $("#uploadPicture"),
            studentAddress = $("#currentAddress"),
            studentState = $("#state"),
            studentCity = $("#city"),
            submit = $("#submit"),
            acceptingModalWindow = $("#example-modal-sizes-title-lg");


    private final CalendarComponent calendarComponent = new CalendarComponent();

    public PracticeFormPage openPracticeFormPage() {
        open(practiceFormPage);

        return this;
    }

    public PracticeFormPage setFirstName(String firstName) {
        firstNameInput.setValue(firstName);

        return this;
    }

    public PracticeFormPage setLastName(String lastName) {
        lastNameInput.setValue(lastName);

        return this;
    }

    public PracticeFormPage setEmail(String studentEmail) {
        email.setValue(studentEmail);

        return this;
    }

    public PracticeFormPage setPhoneNumber(String studentPhoneNumber) {
        phoneNumber.setValue(studentPhoneNumber);

        return this;
    }

    public PracticeFormPage selectGender(String gender) {
        studentGender.$(byText(gender)).click();

        return this;
    }

    public PracticeFormPage setBirthDate(BirthDate birthDate) {
        calendarComponent.setDate(birthDate.getBirthDay(), birthDate.getBirthMonth(), birthDate.getBirthYear());

        return this;
    }

    public PracticeFormPage setStudentSubjects(List<String> subject) {
        subject.forEach(s -> studentSubjects.setValue(s).pressEnter());

        return this;
    }

    public PracticeFormPage selectHobbies(List<String> hobbies) {
        hobbies.forEach(h -> studentHobbies.$(byText(h)).click());

        return this;
    }

    public PracticeFormPage uploadPicture(String fileName) {
        uploadPicture.uploadFromClasspath(fileName);

        return this;
    }

    public PracticeFormPage setStudentAddress(String address) {
        studentAddress.setValue(address);

        return this;
    }

    public PracticeFormPage setStudentState(String state) {
        studentState.scrollTo().click();
        studentState.$(byText(state)).click();

        return this;
    }

    public PracticeFormPage setStudentCity(String city) {
        studentCity.click();
        studentCity.$(byText(city)).scrollTo().click();

        return this;
    }

    public void submit() {
        submit.click();
    }

    public PracticeFormPage validateSubmittingFormTitle() {
        acceptingModalWindow.shouldHave(text("Thanks for submitting the form"));

        return this;
    }

    public PracticeFormPage setStudentModel(StudentModel studentModel) {
        this.studentModel = studentModel;
        this.setFirstName(studentModel.getFirstName())
                .setLastName(studentModel.getLastName())
                .setEmail(studentModel.getEmail())
                .selectGender(studentModel.getGender())
                .setPhoneNumber(studentModel.getMobilePhone())
                .setBirthDate(studentModel.getBirthDate())
                .setStudentSubjects(studentModel.getSubjects())
                .selectHobbies(studentModel.getHobbies())
                .uploadPicture(studentModel.getPicture())
                .setStudentAddress(studentModel.getCurrentAddress())
                .setStudentState(studentModel.getState())
                .setStudentCity(studentModel.getCity());

        return this;
    }

    public PracticeFormPage validateSubmittingFormFields() {
        String browserResponse = getBrowserResponseExecutingJs("get_table_data.js");
        SubmittingFormModel submittingFormModel = convertResponseToModel(browserResponse, SubmittingFormModel.class);
        checkSubmittingForm(submittingFormModel);
        return this;
    }

    private void checkSubmittingForm(SubmittingFormModel submittingFormModel) {
        String regex = "[\\[\\]]";
        assertEquals(studentModel.getFirstName() + " " + studentModel.getLastName(), submittingFormModel.getStudentName());
        assertEquals(studentModel.getEmail(), submittingFormModel.getStudentEmail());
        assertEquals(studentModel.getGender(), submittingFormModel.getGender());
        assertEquals(studentModel.getMobilePhone(), submittingFormModel.getMobile());

        BirthDate birthDate = studentModel.getBirthDate();
        assertEquals(
                birthDate.getBirthDay()
                        + " "
                        + birthDate.getBirthMonth()
                        + ","
                        + birthDate.getBirthYear(),
                submittingFormModel.getDateOfBirth()
        );

        assertEquals(studentModel.getSubjects().toString().replaceAll(regex, ""), submittingFormModel.getSubjects());
        assertEquals(studentModel.getHobbies().toString().replaceAll(regex, ""), submittingFormModel.getHobbies());
        assertEquals(studentModel.getPicture(), submittingFormModel.getPicture());
        assertEquals(studentModel.getCurrentAddress(), submittingFormModel.getAddress());
        assertEquals(studentModel.getState() + " " + studentModel.getCity(), submittingFormModel.getStateAndCity());
    }
}
