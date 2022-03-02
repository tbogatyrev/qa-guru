package hw10.jenkins.params;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import static java.time.format.DateTimeFormatter.ofPattern;
import static java.util.Map.entry;
import static java.util.Map.ofEntries;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PracticeFormTests extends TestBase {

    @Test
    @Tag("registration")
    void studentRegistrationFormTest() {
        Map<String, String> testData = getDataToFill();
        AtomicReference<LocalDate> birthDateData = new AtomicReference<>();

        step("Открываем страницу с формой регистрации", () -> {
            open("/automation-practice-form");
        });

        step("Заполняем поле 'FirstName'", () -> {
            $("#firstName").setValue(testData.get("firstName"));
        });

        step("Заполняем поле 'LastName'", () -> {
            $("#lastName").setValue(testData.get("lastName"));
        });

        step("Заполняем поле 'Email'", () -> {
            $("#userEmail").setValue(testData.get("userEmail"));
        });

        step("Выбираем радиокнопку 'Gender'", () -> {
            $("#genterWrapper").$(byText(testData.get("gender"))).click();
        });

        step("Заполняем поле 'Mobile'", () -> {
            $("#userNumber").setValue(testData.get("mobile"));
        });

        step("Выбираем 'Date of Birth'", () -> {
            $("#dateOfBirthInput").click();
            LocalDate birthDate = LocalDate.of(
                    parseInt(testData.get("year")),
                    parseInt(testData.get("month")),
                    parseInt(testData.get("day"))
            );
            birthDateData.set(birthDate);
            $x("//select[contains(@class, 'year-select')]").selectOption(String.valueOf(birthDate.getYear()));
            $x("//select[contains(@class, 'month-select')]").selectOption(getNameOfMonth(birthDate));
            $x(format("//div[contains(@aria-label, 'Choose %s')]", getBirthDateLocatorString(birthDate))).click();
        });

        step("Заполняем поле 'Subjects'", () -> {
            $("#subjectsInput").setValue(testData.get("subjects")).pressEnter();
        });

        step("Выбираем чекбокс 'Hobbies'", () -> {
            $("#hobbiesWrapper").$(byText(testData.get("hobbies"))).click();
        });

        step("В поле 'Picture' загружаю файл", () -> {
            $("#uploadPicture").uploadFromClasspath(testData.get("picture"));
        });

        step("Заполняем поле 'Current Address'", () -> {
            $("#currentAddress").setValue(testData.get("address"));
        });

        step("Выбираем значение в поле 'Select State'", () -> {
            $("#state").click();
            $("#state").$(byText(testData.get("state"))).scrollTo().click();
        });

        step("Выбираем значение в поле 'Select City'", () -> {
            $("#city").click();
            $("#city").$(byText(testData.get("city"))).click();
        });

        step("Нажимаем на кнопку 'Submit'", () -> {
            $("#submit").click();
        });

        step("В модальное окне отображается текст", () -> {
            $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        });

        Map<String, String> dataToCheck = new HashMap<>();
        $(".modal-body")
                .$("table")
                .$("tbody")
                .$$("tr")
                .forEach((fieldName) -> dataToCheck.put(
                        fieldName.$$("td").first().getText(),
                        fieldName.$$("td").last().getText())
                );

        step("Значение в поле 'Student Name' соответствует ожидаемому", () -> {
            assertEquals(testData.get("firstName") + " " + testData.get("lastName"), dataToCheck.get("Student Name"));
        });

        step("Значение в поле 'Student Email' соответствует ожидаемому", () -> {
            assertEquals(testData.get("userEmail"), dataToCheck.get("Student Email"));
        });

        step("Значение в поле 'Gender' соответствует ожидаемому", () -> {
            assertEquals(testData.get("gender"), dataToCheck.get("Gender"));
        });

        step("Значение в поле 'Mobile' соответствует ожидаемому", () -> {
            assertEquals(testData.get("mobile"), dataToCheck.get("Mobile"));
        });

        step("Значение в поле 'Date of Birth' соответствует ожидаемому", () -> {
            assertEquals(birthDateData.get().format(ofPattern("dd MMMM,yyyy", new Locale("en"))), dataToCheck.get("Date of Birth"));
        });

        step("Значение в поле 'Subjects' соответствует ожидаемому", () -> {
            assertEquals(testData.get("subjects"), dataToCheck.get("Subjects"));
        });

        step("Значение в поле 'Hobbies' соответствует ожидаемому", () -> {
            assertEquals(testData.get("hobbies"), dataToCheck.get("Hobbies"));
        });

        step("Значение в поле 'Picture' соответствует ожидаемому", () -> {
            assertEquals(testData.get("picture"), dataToCheck.get("Picture"));
        });

        step("Значение в поле 'Address' соответствует ожидаемому", () -> {
            assertEquals(testData.get("address"), dataToCheck.get("Address"));
        });

        step("Значение в поле 'State and City' соответствует ожидаемому", () -> {
            assertEquals(testData.get("state") + " " + testData.get("city"), dataToCheck.get("State and City"));
        });
    }

    private Map<String, String> getDataToFill() {
        return ofEntries(
                entry("firstName", "TestFirstName"),
                entry("lastName", "TestLastName"),
                entry("userEmail", "TestUserEmail@example.com"),
                entry("gender", "Male"),
                entry("year", "1901"),
                entry("month", "10"),
                entry("day", "9"),
                entry("mobile", "8912345678"),
                entry("subjects", "Physics"),
                entry("hobbies", "Music"),
                entry("picture", "testFile.jpg"),
                entry("address", "Test Current Address"),
                entry("state", "NCR"),
                entry("city", "Karnal")
        );
    }

    private String getNameOfMonth(LocalDate date) {
        return date.format(ofPattern("MMMM", new Locale("en")));
    }

    private String getBirthDateLocatorString(LocalDate birthDate) {
        return birthDate.format(ofPattern("EEEE, MMMM d", new Locale("en")));
    }
}
