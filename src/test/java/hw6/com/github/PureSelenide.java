package hw6.com.github;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PureSelenide extends TestBase {

    @Test
    void pureSelenideIssueTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open(REPOSITORY);

        $("h1").shouldHave(text("tbogatyrev / qa-guru"));
        $("#issues-tab").should(visible);
        $("#issues-tab").click();
    }
}
