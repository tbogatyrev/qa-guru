package hw6.com.github.steps;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;

import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WebSteps {

    @Step("Открываем странице с репозиторием {string}")
    public void openRepository(String repositoryPath) {
        open(repositoryPath);
    }

    @Step("Название репозитория пристутствует")
    public void isRepositoryVisible() {
        $("h1").shouldHave(text("tbogatyrev / qa-guru"));
    }

    @Step("Вкладка Issue присутствует")
    public void isIssueTabVisible() {
        $("#issues-tab").should(visible);
    }

    @Step("Нажимаю на вкладку Issue")
    public void clickOnIssueTab() {
        $("#issues-tab1").click();
    }

    @Attachment(value = "Screenshot", type = "text/html", fileExtension = "html")
    public byte[] attachPageSource() {
        return WebDriverRunner.source().getBytes(StandardCharsets.UTF_8);
    }
}
