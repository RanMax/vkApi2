package net.acyuta.vk.exceptions;

import com.google.gson.JsonObject;

/**
 * Created by acyuta on 09.12.14.
 */
public class UnknownResponse extends AbstractJsonException {
    public UnknownResponse(String message, JsonObject object) {
        super(message, object);
    }
}
