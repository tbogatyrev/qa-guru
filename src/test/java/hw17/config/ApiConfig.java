package hw17.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config/api.properties")
public interface ApiConfig extends Config {

    @Key("base.url")
    String baseUrl();
}
