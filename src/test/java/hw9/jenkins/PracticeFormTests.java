package hw9.jenkins;

import hw9.jenkins.pages.PracticeFormPage;
import hw9.jenkins.pages.models.StudentModel;
import org.junit.jupiter.api.Test;

import java.util.List;

import static hw9.jenkins.TestData.*;

public class PracticeFormTests extends TestBase {

    PracticeFormPage practiceFormPage = new PracticeFormPage();
    StudentModel studentModel = new StudentModel();

    @Test
    void studentRegistrationFormTest() {
        studentModel
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(studentEmail)
                .setGender("Male")
                .setMobilePhone(studentPhoneNumber)
                .setBirthDate("09", "October", "1901")
                .setSubjects(List.of("Physics", "English"))
                .setHobbies(List.of("Music", "Sports"))
                .setPicture("testFile.jpg")
                .setCurrentAddress(address)
                .setState("Haryana")
                .setCity("Karnal");

        practiceFormPage
                .openPracticeFormPage()
                .setStudentModel(studentModel)
                .submit();

        practiceFormPage
                .validateSubmittingFormTitle()
                .validateSubmittingFormFields();
    }
}
