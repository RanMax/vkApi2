package net.acyuta.vk.api.messages;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.acyuta.vk.api.AbstractVkMethod;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.Arrays;
import java.util.List;

/**
 * Created by acyuta on 10.12.14.
 */
public class GetDialogs extends AbstractVkMethod {

    private static List<NameValuePair> defaultConfig = Arrays.asList(
            (NameValuePair) new BasicNameValuePair("count", "20"),
            (NameValuePair) new BasicNameValuePair("preview_length", "0"),
            (NameValuePair) new BasicNameValuePair("unread", "1")
    );
    private int unreadDialogs;

    @Override
    public boolean recognize(JsonObject response) {
        if (response == null)
            return false;
        unreadDialogs = response.get("count").getAsInt();
        JsonArray items = response.getAsJsonArray("items");

        return true;
    }

    @Override
    public List<NameValuePair> getArgs() {
        return defaultConfig;
    }

    @Override
    public String getName() {
        return "messages.getDialogs";
    }
}
