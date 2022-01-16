package hw6.com.github;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class LambdasSteps extends TestBase {

    @Test
    public void lambdasStepsTest() {
        step("Открываем странице с репозиторием", () -> {
            open("/tbogatyrev/qa-guru");
        });
        step("Название репозитория пристутствует", () -> {
            $("h1").shouldHave(text("tbogatyrev / qa-guru"));
        });
        step("Вкладка Issue присутствует", () -> {
            $("#issues-tab").should(visible);
        });
        step("Нажимаю на вкладку Issue", () -> {
            $("#issues-tab1").click();
        });
    }

    @AfterEach
    public void saveSources() {
        Allure.addAttachment("Page Source", "text/html", WebDriverRunner.source(), "html");

    }
}
