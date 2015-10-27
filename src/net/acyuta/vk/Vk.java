package net.acyuta.vk;

import net.acyuta.utils.Alias;
import net.acyuta.vk.exceptions.ConfigurationException;
import net.acyuta.vk.exceptions.NoConfigDirException;
import net.acyuta.vk.exceptions.NoConfigFileException;
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

    public static final String PROGRAM_NAME = "vkc";
    public static String CONFIG_FILENAME = "property.ini";
    private static Vk implement = null;

    /**
     * Идентификатор моего приложения
     */
    private String redirect_url = "http://oauth.vk.com/blank.html";
    private String api_version = null;
    private String app_code = null;
    private String app_id = "5122354";
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
        if (implement != null)
            return implement;
        else throw new IllegalAccessError("Еще не инициирован класс API Vk");
    }

    public static Vk init() throws ConfigurationException, NoConfigDirException, NoConfigFileException {
        String filename = CONFIG_FILENAME;
        if (new File(filename).exists())
            return init(filename);
        else {
            String sep = System.getProperty("file.separator");
            File configDir = new File(Alias.configDirPath);
            if (configDir.exists() && configDir.isDirectory() && configDir.canWrite()) {
                File configFile = new File(Alias.homeConfigFile);
                if (configFile.exists())
                    return init(configFile.getAbsolutePath());
                else filename = Alias.homeConfigFile;

            } else throw new NoConfigDirException("Отсутствует папка конфигурации '" + Alias.configDirPath + "'");
        }
        throw new NoConfigFileException("Отсутствует файл конфигурации '" + filename + "'");
    }

    public static Vk init(String filename) throws ConfigurationException {
        Properties props = new Properties();

        try {
            props.load(new FileInputStream(new File(filename)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        implement = new Vk();

        implement.app_id = props.getProperty("app_id", null);
        if (implement.app_id == null)
            throw new ConfigurationException("No 'app_id' field");

        implement.app_code = props.getProperty("app_code", null);
        if (implement.app_code == null)
            throw new ConfigurationException("No 'app_code' field");

        implement.auth_url = props.getProperty("auth_url", null);
        if (implement.auth_url == null)
            throw new ConfigurationException("No 'auth_url' field");

        implement.method_url = props.getProperty("method_url", null);
        if (implement.method_url == null)
            throw new ConfigurationException("No 'method_url' field");

        implement.token = props.getProperty("token", null);
        if (implement.token == null)
            throw new ConfigurationException("No 'token' field");

        implement.userId = Integer.valueOf(props.getProperty("user_id", "-1"));
        assert implement.userId != -1;

        implement.api_version = "5.37";

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
                .append(implement.api_version)
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

    public String getVersion() {
        return api_version;
    }
}
