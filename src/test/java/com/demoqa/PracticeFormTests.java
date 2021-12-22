package com.demoqa;

import com.codeborne.selenide.Selenide;
import com.demoqa.utils.calendar.BirthDate;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Locale;

import static com.codeborne.selenide.Configuration.browserSize;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PracticeFormTests {

    @BeforeAll
    static void beforeAll() {
        browserSize = "1920x1080";
    }

    @Test
    void studentRegistrationFormTest() {
        open("https://demoqa.com/automation-practice-form");

//        uploadPicture
//        currentAddress
//        stateCity-wrapper
//        city
//        submit
        $("#firstName").setValue("TestFirstName");
        $("#lastName").setValue("TestLastName");
        $("#userEmail").setValue("TestUserEmail@example.com");
        $("#gender-radio-1~label.custom-control-label").click();
        $("#userNumber").setValue("8912345678");
        $("#dateOfBirthInput").click();

        LocalDate birthDate = LocalDate.of(1901, 10, 9);
        $x("//select[contains(@class, 'year-select')]").selectOption(String.valueOf(birthDate.getYear()));
        $x("//select[contains(@class, 'month-select')]").selectOption(getNameOfMonth(birthDate));
        $x(String.format("//div[contains(@aria-label, 'Choose %s')]", getBirthDateLocatorString(birthDate))).click();

        $("#subjectsInput").setValue("Test Subjects");
        $("#hobbies-checkbox-3~label").click();
        $("#currentAddress").setValue("Test Current Address");
        $("#stateCity-wrapper").click();

    }

    @AfterAll
    static void afterAll() {
        closeWebDriver();
    }

    private String getNameOfMonth(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMMM", new Locale("en")));
    }

    public static String getBirthDateLocatorString(LocalDate birthDate) {
        return birthDate.format(DateTimeFormatter.ofPattern("EEEE, MMMM d", new Locale("en")));
    }
}
