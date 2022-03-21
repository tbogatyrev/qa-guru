package hw18.com.tricentis.demowebshop.helpers;

import com.codeborne.selenide.Configuration;
import hw18.com.tricentis.demowebshop.config.ConfigHelper;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import static org.openqa.selenium.PageLoadStrategy.EAGER;

public class DriverSettings {

    public static void configure() {
        Configuration.browser = ConfigHelper.getBrowser();
        Configuration.browserVersion = ConfigHelper.getBrowserVersion();
        Configuration.browserSize = ConfigHelper.getBrowserSize();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        ChromeOptions chromeOptions = new ChromeOptions();

        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-infobars");
        chromeOptions.addArguments("--disable-popup-blocking");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--lang=en-en");
        chromeOptions.setPageLoadStrategy(EAGER);

        if (ConfigHelper.isRemoteWebDriver()) {
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
            Configuration.remote = String.format(
                    "http://%s", ConfigHelper.getRemoteDriverUrl()
            );
//            Configuration.remote = String.format(
//                    "https://%s:%s@%s", System.getProperty("login"),
//                    System.getProperty("password"),
//                    Project.config.remoteDriverUrl()
//            );
        }

        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        Configuration.browserCapabilities = capabilities;
    }
}
