package net.acyuta.vk.api.messages;

import com.google.gson.JsonObject;
import net.acyuta.vk.api.AbstractVkMethod;

/**
 * Created by acyuta on 18.01.15.
 */
public class GetChat extends AbstractVkMethod {
    @Override
    public boolean recognize(JsonObject response) {
        if (response == null)
            return false;

        return true;
    }

    @Override
    public String getName() {
        return "messages.GetChat";
    }
}
