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
    private static Vk implement = null;

    /**
     * Идентификатор моего приложения
     */
    private String redirect_url = null;
    private String api_id = null;
    private String app_code = null;
    private String app_id = null;
    private List<String> scope = null;
    private String method_url = null;
    private String auth_url = null;
    private String authUrl = null;
    private String token = null;
    private int userId = -1;
    private Handler handler = null;

    /* Служебная часть */
    private Vk() {
    }

    public static Vk getImplements() {
        if (implement == null)
            return implement;
        throw new IllegalAccessError("Еще не инициирован класс API Vk");
    }

    public static Vk init() throws IOException {
        return init(propertyDefaultFilename);
    }

    public static Vk init(String filename) throws IOException {
        Properties props = new Properties();

        props.load(new FileInputStream(new File(filename)));

        implement = new Vk();

        implement.app_id = props.getProperty("app_id", null);
        assert implement.app_id != null;

        implement.app_code = props.getProperty("app_code", null);
        assert implement.app_code != null;

        implement.auth_url = props.getProperty("auth_url", null);
        assert implement.auth_url != null;

        implement.method_url = props.getProperty("method_url", null);
        assert implement.method_url != null;

        implement.token = props.getProperty("token", null);
        assert implement.token != null;

        implement.userId = Integer.valueOf(props.getProperty("user_id", "-1"));
        assert implement.userId != -1;

        implement.api_id = "5.27";

        implement.redirect_url = "https://oauth.vk.com/blank.html";

        implement.scope = Arrays.asList("messages", "notifications", "offline", "status", "friends");

        implement.authUrl = (new StringBuilder())
                .append(implement.auth_url)
                .append('?')
                .append("client_id=")
                .append(implement.app_id)
                .append("&redirect_uri=")
                .append(implement.redirect_url)
                .append("&scope=")
                .append(StringUtils.join(implement.scope, ','))
                .append("&display=page")
                .append("&v=")
                .append(implement.api_id)
                .append("&response_type=token")
                .toString();


        implement.handler = new Handler(implement);

        return implement;
    }

    public String getAuthUrl() {
        return authUrl;
    }

    public String getMethod_url() {
        return method_url;
    }

    public Handler getHandler() {
        return handler;
    }

    public String getToken() {
        return token;
    }

    /* Логическая часть */

    public boolean checkAccess() {
        return (token != null && userId != -1) && checkPermissions();
    }

    private boolean checkPermissions() {
        //TODO Сделать проверочку
        return true;
    }

    public String usage() {
        return (new StringBuilder())
                .append("При первом использовании, пройдите по ссылке \n")
                .append(getAuthUrl())
                .append("\nи добавьте поля \ntoken = <ваш код>\nuser_id = <ваш_ID>\nиз адресной строки в ваш файл конфигурации ")
                .append(propertyDefaultFilename)
                .toString();
    }

}
