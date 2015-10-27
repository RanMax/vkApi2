package net.acyuta.vk.exceptions;

import com.google.gson.JsonObject;

/**
 * Created by acyuta on 09.12.14.
 */
abstract public class AbstractJsonException extends RuntimeException {

    JsonObject object = null;

    public AbstractJsonException(String message, JsonObject object) {
        super(message);
        this.object = object;
    }

    public JsonObject getObject() {
        return object;
    }
}
