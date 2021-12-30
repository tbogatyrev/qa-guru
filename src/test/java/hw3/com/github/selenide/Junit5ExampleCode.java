package hw3.com.github.selenide;

import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Configuration.browserSize;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class Junit5ExampleCode {

    @BeforeAll
    static void beforeAll() {
        browserSize = "1920x1080";
        baseUrl = "https://github.com";
    }

//    2. Разработайте следующий автотест:
// - Откройте страницу Selenide в Github
// - Перейдите в раздел Wiki проекта
// - Убедитесь, что в списке страниц (Pages) есть страница SoftAssertions
// - Откройте страницу SoftAssertions, проверьте что внутри есть пример кода для JUnit5

    @Test
    void testJunit5ExampleCodeExist() {
        open("/selenide/selenide");

        $("h1").shouldHave(text("selenide / selenide"));
        $("#wiki-tab").click();
        $("#wiki-pages-box button").scrollTo().click();
        $("#wiki-pages-box").shouldHave(text("SoftAssertions"));
        $("#wiki-pages-box").$(Selectors.byText("SoftAssertions")).click();
        String junit5Header = "Using JUnit5 extend test class:";
        String exampleJUnit5Code = "@ExtendWith({SoftAssertsExtension.class})\n" +
                "class Tests {\n" +
                "  @Test\n" +
                "  void test() {\n" +
                "    Configuration.assertionMode = SOFT;\n" +
                "    open(\"page.html\");\n" +
                "\n" +
                "    $(\"#first\").should(visible).click();\n" +
                "    $(\"#second\").should(visible).click();\n" +
                "  }\n" +
                "}";
        $("#wiki-body")
                .$("ol[start='3']")
                .scrollTo()
                .shouldHave(text(junit5Header))
                .parent()
                .$("ol[start='3']~div")
                .shouldHave(text(exampleJUnit5Code));
    }
}
