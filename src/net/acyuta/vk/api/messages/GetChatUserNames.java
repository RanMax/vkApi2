package net.acyuta.vk.api.messages;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.acyuta.vk.api.messages.utils.User;

import java.util.HashMap;

/**
 * Created by acyuta on 18.01.15.
 */
public class GetChatUserNames extends GetChat {


    HashMap<Long,User> map = new HashMap<Long, User>();

    @Override
    public boolean recognize(JsonObject response) {
        if (response == null)
            return false;
        JsonArray users = response.get("users").getAsJsonArray();
        for (JsonElement e : users) {
            JsonObject user = e.getAsJsonObject();
            User u = new User();
            u.id = user.get("id").getAsInt();
            u.first_name = user.get("first_name").getAsString();
            u.last_name = user.get("last_name").getAsString();
            if (user.has("nickname") && user.get("nickname").getAsString().length() > 3)
                u.nickname = user.get("nickname").getAsString();
            else
                u.nickname = u.first_name + ' ' + u.last_name;
            map.put(u.id,u);
        }
        return true;
    }

    public HashMap<Long, User> getUserMap() {
        return map;
    }
}
