package hw10.jenkins.params;

import hw10.jenkins.params.utils.Attach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static hw10.jenkins.params.config.Credentials.credentials;

public class TestBase {

    @BeforeAll
    static void beforeAll() {
        browserSize = System.getProperty("browserSize");
        baseUrl = "https://demoqa.com";
        remote = String.format(
                "https://%s:%s@%s", credentials.login(),
                credentials.password(),
                System.getProperty("remoteUrl")
        );
        browser = System.getProperty("browser");
        browserVersion = System.getProperty("version");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        capabilities.setCapability("name", "tbogatyrev");
        browserCapabilities = capabilities;
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }
}
