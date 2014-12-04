package net.acyuta.vk;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Created by acyuta on 05.12.14.
 */
public class Vk {

    private static String propertyDefaultFilename = "property.ini";

    private String authUrl;
    private String code;
    /**
     * Идентификатор моего приложения
     */
    private static String redirect_url;

    private static String api_id;
    private static String app_code;
    private static String app_id;
    private static List<String> scope;
    private static String url;


    public Vk() throws IOException {
        init(propertyDefaultFilename);
    }

    public Vk(String filename) throws IOException {
        init(filename);
    }

    public String getAuthUrl() {
        return authUrl;
    }

    private boolean init(String filename) throws IOException {
        Properties props = new Properties();

        props.load(new FileInputStream(new File(filename)));

        app_id = props.getProperty("app_id",null);
        assert app_id != null;

        app_code = props.getProperty("app_code",null);
        assert app_code != null;

        url = props.getProperty("url",null);
        assert url != null;

        code = props.getProperty("code",null);

        api_id = "5.27";

        redirect_url = "https://oauth.vk.com/blank.html";

        scope = Arrays.asList("messages","notifications","offline","status","friends");

        authUrl = (new StringBuilder())
                .append(url)
                .append('?')
                .append("client_id=")
                .append(app_id)
                .append("&redirect_uri=")
                .append(redirect_url)
                .append("&scope=")
                .append(StringUtils.join(scope,','))
                .append("&display=page")
                .append("&v=")
                .append(api_id)
                .toString();

        return true;
    }
}
