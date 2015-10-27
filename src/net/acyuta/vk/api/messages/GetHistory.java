package net.acyuta.vk.api.messages;

import com.google.gson.JsonObject;
import net.acyuta.vk.api.AbstractVkMethod;

/**
 * Created by acyuta on 18.01.15.
 */
public class GetHistory extends AbstractVkMethod {
    @Override
    public boolean recognize(JsonObject response) {
        return false;
    }

    @Override
    public String getName() {
        return "messages.getHistory";
    }
}
