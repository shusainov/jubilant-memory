package helpers.props;


import org.aeonbits.owner.Config;

@Config.Sources("classpath:props.properties")
public interface Props extends Config {
    @Key("remote.driver.url")
    String remoteDriverUrl();

    @Key("test.run.mode")
    String testRunMode();

    @Key("start.page.url")
    String startPageUrl();

}
