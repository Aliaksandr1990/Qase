package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"system:properties",
        "classpath:config/driver.properties"})

public interface CredentialsConfig extends Config {

    @Key("email")
    String getEmail();

    @Key("password")
    String getPassword();

    @Key("token")
    String getToken();
}
