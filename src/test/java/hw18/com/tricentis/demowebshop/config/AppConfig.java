package hw18.com.tricentis.demowebshop.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/hw18/app.properties"
})
public interface AppConfig extends Config {

    @Config.Key("base.url")
    String baseUrl();
}
