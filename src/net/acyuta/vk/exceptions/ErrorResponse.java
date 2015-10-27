package net.acyuta.vk.exceptions;

import com.google.gson.JsonObject;

/**
 * Created by acyuta on 09.12.14.
 */
public class ErrorResponse extends AbstractJsonException {

    public ErrorResponse(String message, JsonObject object) {
        super(message, object);
    }
}
