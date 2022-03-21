package hw18.com.tricentis.demowebshop;


import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import hw18.com.tricentis.demowebshop.config.ConfigHelper;
import hw18.com.tricentis.demowebshop.helpers.AllureAttachments;
import hw18.com.tricentis.demowebshop.helpers.DriverSettings;
import hw18.com.tricentis.demowebshop.helpers.DriverUtils;
import hw18.com.tricentis.demowebshop.pages.MainPage;
import hw18.com.tricentis.demowebshop.steps.ApiSteps;
import io.qameta.allure.junit5.AllureJunit5;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({AllureJunit5.class})
public class TestBase {

    static ApiSteps apiSteps;
    static MainPage mainPage;

    @BeforeAll
    public static void setUp() {
        apiSteps = new ApiSteps();
        mainPage = new MainPage();
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        DriverSettings.configure();
    }

    @AfterEach
    public void addAttachments() {
        String sessionId = DriverUtils.getSessionId();

        AllureAttachments.addScreenshotAs("Last screenshot");
        AllureAttachments.addPageSource();
        AllureAttachments.addBrowserConsoleLogs();

        Selenide.closeWebDriver();

        if (ConfigHelper.isVideoOn()) {
            AllureAttachments.addVideo(sessionId);
        }
    }
}
