package hw17.config;

import org.aeonbits.owner.ConfigFactory;

public class ConfigHelper {

    public static String getBaseUrl() {
        return getConfig().baseUrl();
    }

    private static ApiConfig getConfig() {
        return ConfigFactory.create(ApiConfig.class);
    }
}
