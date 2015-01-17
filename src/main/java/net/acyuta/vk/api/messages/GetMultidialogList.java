package net.acyuta.vk.api.messages;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.acyuta.vk.api.VkMethod;
import net.acyuta.vk.api.messages.utils.ChatDialog;
import net.acyuta.vk.exceptions.UnrecognizedResponse;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by acyuta on 18.01.15.
 */
public class GetMultidialogList extends GetDialogs {

    /* использую линковый, чтобы последовательность сохранялась */
    private LinkedHashMap<Integer, ChatDialog> map = null;

    @Override
    public boolean recognize(JsonObject response) {
        if (response == null)
            return false;
        count = response.get("count").getAsInt();
        if (response.has("items") && !response.get("items").isJsonNull()) {
            JsonArray items = response.getAsJsonArray("items");
            for (JsonElement item : items) {
                JsonObject obj = item.getAsJsonObject().get("message").getAsJsonObject();
                if (obj.has("chat_id")) {
                    int id = obj.get("chat_id").getAsInt();
                    ChatDialog dialog = new ChatDialog(
                            String.valueOf(id),
                            obj.get("title").getAsString(),
                            obj.get("body").getAsString(),
                            obj.get("date").getAsString(),
                            obj.get("user_id").getAsString()
                    );
                    map.put(id, dialog);
                }

            }
            return true;
        }
        return false;
    }

    @Override
    public VkMethod execute() throws UnrecognizedResponse {
        //Ленивое создание
        if (map == null)
            map = new LinkedHashMap<Integer, ChatDialog>();
        return super.execute();
    }

    public Map<Integer, ChatDialog> getSet() {
        return this.map;
    }
}
