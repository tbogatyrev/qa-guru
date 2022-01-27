package hw8.parametrization;

import hw6.com.github.TestBase;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Configuration.browserSize;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class Junit5Parametrization extends TestBase {

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
    @ParameterizedTest(name = "Тестирование расчета суммы ежемесячного платежа в зависимости от срока кредита с тестовыми данными: {0} {1}")
    void methodSourceTest(String testData, String expected) {
        open(baseUrl);
        $("div[data-test-id='calculator-layout']")
                .$(byText(testData))
                .click();
        $("div[data-test-id='calcLayout']")
                .$(byText("Наше предложение"))
                .ancestor("div")
                .shouldHave(text(expected));
    }

    @CsvSource({
            "1 год, 118 900 ₽",
            "2 года, 62 500 ₽",
            "3 года, 43 800 ₽"
    })
    @ParameterizedTest(name = "Тестирование расчета суммы ежемесячного платежа в зависимости от срока кредита с тестовыми данными: {0}")
    void csvSourceTest(String testData, String expected) {
        open(baseUrl);
        $("div[data-test-id='calculator-layout']")
                .$(byText(testData))
                .click();
        $("div[data-test-id='calcLayout']")
                .$(byText("Наше предложение"))
                .ancestor("div")
                .shouldHave(text(expected));
    }
}
