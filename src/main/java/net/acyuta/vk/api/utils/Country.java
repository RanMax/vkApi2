package net.acyuta.vk.api.utils;

import com.google.gson.JsonObject;

/**
 * Created by acyuta on 09.12.14.
 */
public class Country extends AbstractIdTitlePair {
    public Country(int id, String title) {
        super(id, title);
    }

    public Country(JsonObject jsonObject) {
        super(jsonObject);
    }
}
