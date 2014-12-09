package net.acyuta.utils;

import net.acyuta.vk.Vk;

/**
 * Created by acyuta on 09.12.14.
 */
public class Alias {
    public static String sep = System.getProperty("file.separator");
    public static String configDirPath = System.getProperty("user.home") + sep + "." + Vk.PROGRAM_NAME;
    public static String homeConfigFile = configDirPath + sep + Vk.CONFIG_FILENAME;
}
