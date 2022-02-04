package yanot;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;


import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class HomeWorkParameterized {

    @BeforeEach
    void beforeEach() {
        open("https://ru.wikipedia.org/");
    }

    @AfterEach
    void closeBrowser() {
        Selenide.closeWebDriver();
    }

    static Stream<Arguments> commonSearchDataProvider() {
        return Stream.of(
                Arguments.of("Metallica", "Metallica была основана в Лос-Анджелесе 28 октября 1981 года"),
                Arguments.of("Хетфилд", "вокалист и ритм-гитарист метал-группы Metallica"),
                Arguments.of("Богемская", "песня британской рок-группы Queen из альбома A Night at the Opera"),
                Arguments.of("Фредди", "британский певец парсийского происхождени")
        );
    }

    @Disabled
    @ValueSource(strings = {"Металлика", "Джеймс Хетфилд"})
    @ParameterizedTest(name = "Тестирование с тестданными: {0}")
    void searchValueSource(String testData) {
        $("#mw-head").$("#p-search").click();
        $("#searchInput").setValue(testData);
        $("#searchButton").pressEnter();
        $("#content").shouldHave(text(testData));
    }

    @Disabled
    @CsvSource(value = {"Металлика, Metallica была основана в Лос-Анджелесе 28 октября 1981 года",
            "Джеймс Хетфилд, вокалист и ритм-гитарист метал-группы Metallica", "Богемская рапсодия, песня британской рок-группы Queen из альбома A Night at the Opera"})
    @ParameterizedTest(name = "Тестирование с тестданными: {0}")
    void searchCsvSource(String testData, String expectedResult) {
        $("#mw-head").$("#p-search").click();
        $("#searchInput").setValue(testData);
        $("#searchButton").pressEnter();
        $("#content").shouldHave(text(expectedResult));
    }

    @MethodSource("commonSearchDataProvider")
    @ParameterizedTest(name = "Тестирование с тестданными: {0}")
    void searchWithMethodSourceTest(String testData, String expectedResult) {
        $("#mw-head").$("#p-search").click();
        $("#searchInput").setValue(testData);
        $("#searchButton").pressEnter();
        $("#content").shouldHave(text(expectedResult));
    }
}