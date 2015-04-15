package net.acyuta.vk.api;

import com.google.gson.JsonObject;

/**
 * Created by acyuta on 10.12.14.
 */
public interface RecognizeJson {

    abstract boolean recognize(JsonObject response);
}
