package net.acyuta.vk.api;

/**
 * Created by acyuta on 08.12.14.
 */

/**
 * Интерфейс метода для запроса к API вконтакта
 */
public interface VkMethod {
    String getName();

    VkMethod execute();
}
