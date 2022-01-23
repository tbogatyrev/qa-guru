package hw8.parametrization;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import hw6.com.github.TestBase;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Configuration.browserSize;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class Junit5Parametrization extends TestBase {

    static String str;

    @BeforeAll
    static void beforeAll() {
        browserSize = "1920x1080";
        baseUrl = "https://alfabank.ru/";
    }

    static Stream<Arguments> commonTestValueSource() {
        return Stream.of(
                Arguments.of("1 год", "118 900 ₽"),
                Arguments.of("2 года", "62 500 ₽"),
                Arguments.of("3 года", "43 800 ₽")
        );
    }


    @MethodSource("commonTestValueSource")
    @ParameterizedTest(name = "Тестирование расчета суммы ежемесячного платежа в зависимости от срока кредита с тестовыми данными: {0}")
    void commonSearchTest(String testData, String expected) {
        open(baseUrl);
        $("div[data-test-id='calculator-layout']").$(byText(testData)).click();
        $("div[data-test-id='calcLayout']").$(byText("Наше предложение")).ancestor("div").shouldHave(text(expected));
    }


    // В результатах поиска в яндексе есть искомое слово
    // * потворить для тестовых данных: [Allure, Selenide]

    // Открыть яндекс
    // Вбить в поле поиска {test_data}
    // кликнуть кнопку найти


    // в первом результате есть слово {test_data}
}
