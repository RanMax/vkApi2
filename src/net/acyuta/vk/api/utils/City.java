package net.acyuta.vk.api.utils;

import com.google.gson.JsonObject;

/**
 * Created by acyuta on 09.12.14.
 */
public class City extends AbstractIdTitlePair {
    public City(int id, String title) {
        super(id, title);
    }

    public City(JsonObject jsonObject) {
        super(jsonObject);
    }
}
