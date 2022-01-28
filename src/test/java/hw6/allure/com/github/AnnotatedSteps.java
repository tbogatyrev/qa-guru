package hw6.allure.com.github;

import hw6.allure.com.github.steps.WebSteps;
import io.qameta.allure.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AnnotatedSteps extends TestBase {

    WebSteps steps = new WebSteps();

    @Test
    @Owner("tbogatyrev")
    @Feature("Issue")
    @Stories({@Story("Проверка отображения вкладки Issue")})
    @DisplayName("Проверка отображения вкладки Issue")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "GitHub", url = "https://github.com")
    public void annotatedStepsTest() {
        Allure.parameter("Репозиторий", REPOSITORY);
        steps.openRepository(REPOSITORY);
        steps.isRepositoryVisible();
        steps.isIssueTabVisible();
        steps.clickOnIssueTab();
    }

    @AfterEach
    public void saveSources() {
        steps.attachPageSource();
    }
}
