package hw18.com.tricentis.demowebshop.config;

import org.aeonbits.owner.ConfigFactory;

public class ConfigHelper {

    public static String getBaseUrl() {
        return getAppConfig().baseUrl();
    }

    public static String getBrowser() {
        return getProjectConfig().browser();
    }

    public static String getBrowserVersion() {
        return getProjectConfig().browserSize();
    }

    public static String getBrowserSize() {
        return getProjectConfig().browserSize();
    }

    public static boolean isRemoteWebDriver() {
        return !getProjectConfig().remoteDriverUrl().equals("");
    }

    public static String getRemoteDriverUrl() {
        return getProjectConfig().remoteDriverUrl();
    }

    public static String getVideoStorage() {
        return getProjectConfig().videoStorage();
    }

    public static boolean isVideoOn() {
        return !getProjectConfig().videoStorage().equals("");
    }

    private static AppConfig getAppConfig() {
        return ConfigFactory.create(AppConfig.class, System.getProperties());
    }

    private static ProjectConfig getProjectConfig() {
        return ConfigFactory.create(ProjectConfig.class, System.getProperties());
    }
}
