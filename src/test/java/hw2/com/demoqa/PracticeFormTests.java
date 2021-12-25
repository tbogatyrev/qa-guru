package hw2.com.demoqa;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Configuration.browserSize;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import static java.time.format.DateTimeFormatter.ofPattern;
import static java.util.Map.entry;
import static java.util.Map.ofEntries;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PracticeFormTests {

    @BeforeAll
    static void beforeAll() {
        browserSize = "1920x1080";
        baseUrl = "https://demoqa.com";
    }

    @Test
    void studentRegistrationFormTest() {
        Map<String, String> testData = getDataToFill();

        open("/automation-practice-form");

        $("#firstName").setValue(testData.get("firstName"));
        $("#lastName").setValue(testData.get("lastName"));
        $("#userEmail").setValue(testData.get("userEmail"));
        $("#genterWrapper").$(byText(testData.get("gender"))).click();
        $("#userNumber").setValue(testData.get("mobile"));

        $("#dateOfBirthInput").click();
        LocalDate birthDate = LocalDate.of(
                parseInt(testData.get("year")),
                parseInt(testData.get("month")),
                parseInt(testData.get("day"))
        );
        $x("//select[contains(@class, 'year-select')]").selectOption(String.valueOf(birthDate.getYear()));
        $x("//select[contains(@class, 'month-select')]").selectOption(getNameOfMonth(birthDate));
        $x(format("//div[contains(@aria-label, 'Choose %s')]", getBirthDateLocatorString(birthDate))).click();

        $("#subjectsInput").setValue(testData.get("subjects")).pressEnter();
        $("#hobbiesWrapper").$(byText(testData.get("hobbies"))).click();
        $("#uploadPicture").uploadFromClasspath(testData.get("picture"));
        $("#currentAddress").setValue(testData.get("address"));

        $("#state").click();
        $("#state").$(byText(testData.get("state"))).scrollTo().click();

        $("#city").click();
        $("#city").$(byText(testData.get("city"))).click();

        $("#submit").click();

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));

        Map<String, String> dataToCheck = new HashMap<>();
        $(".modal-body")
                .$("table")
                .$("tbody")
                .$$("tr")
                .forEach((fieldName) -> dataToCheck.put(
                        fieldName.$$("td").first().getText(),
                        fieldName.$$("td").last().getText())
                );

        assertEquals(testData.get("firstName") + " " + testData.get("lastName"), dataToCheck.get("Student Name"));
        assertEquals(testData.get("userEmail"), dataToCheck.get("Student Email"));
        assertEquals(testData.get("gender"), dataToCheck.get("Gender"));
        assertEquals(testData.get("mobile"), dataToCheck.get("Mobile"));
        assertEquals(birthDate.format(ofPattern("dd MMMM,yyyy", new Locale("en"))), dataToCheck.get("Date of Birth"));
        assertEquals(testData.get("subjects"), dataToCheck.get("Subjects"));
        assertEquals(testData.get("hobbies"), dataToCheck.get("Hobbies"));
        assertEquals(testData.get("picture"), dataToCheck.get("Picture"));
        assertEquals(testData.get("address"), dataToCheck.get("Address"));
        assertEquals(testData.get("state") + " " + testData.get("city"), dataToCheck.get("State and City"));
    }

    @AfterAll
    static void afterAll() {
        closeWebDriver();
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
                entry("state", "Haryana"),
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
