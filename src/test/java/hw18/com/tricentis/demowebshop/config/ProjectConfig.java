package hw18.com.tricentis.demowebshop.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/hw18/local.properties",
        "classpath:config/hw18/remote.properties"
})
public interface ProjectConfig extends Config {

    @DefaultValue("chrome")
    String browser();

    @DefaultValue("91.0")
    String browserVersion();

    @DefaultValue("1920x1080")
    String browserSize();

    String browserMobileView();

    String remoteDriverUrl();

    String videoStorage();
}
