package net.acyuta.vk.api.utils;

import com.google.gson.JsonObject;

/**
 * Created by acyuta on 09.12.14.
 */
public class AbstractIdTitlePair {
    public int id;
    public String title;

    public AbstractIdTitlePair(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public AbstractIdTitlePair(JsonObject jsonObject) {
        this.id = jsonObject.get("cid").getAsInt();
        this.title = jsonObject.get("title").getAsString();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
