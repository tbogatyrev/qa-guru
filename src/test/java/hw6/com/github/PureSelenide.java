package hw6.com.github;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PureSelenide extends TestBase {

    @Test
    void pureSelenideIssueTest() {
        open("/tbogatyrev/qa-guru");

        $("h1").shouldHave(text("tbogatyrev / qa-guru"));
        $("#issues-tab").should(visible);
        $("#issues-tab1").click();
    }
}
