package net.acyuta.vk.api.messages;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sun.istack.internal.NotNull;
import net.acyuta.vk.api.AbstractVkMethod;
import org.apache.http.NameValuePair;

import java.util.Collection;

/**
 * Created by acyuta on 10.12.14.
 */
public class GetDialogs extends AbstractVkMethod {

    int count;
    private boolean unreadArgument;

    public boolean isUnread() {
        return unreadArgument;
    }

    @Override
    public boolean recognize(JsonObject response) {
        if (response == null)
            return false;
        count = response.get("count").getAsInt();
        JsonArray items = response.getAsJsonArray("items");
        //TODO разобрать сообщения
        return true;
    }

    @Override
    public void setArgs(@NotNull Collection<NameValuePair> args) {
        this.unreadArgument = false;
        for (NameValuePair pair : args)
            if (pair.getName().equals("unread"))
                this.unreadArgument = true;
        super.setArgs(args);
    }

    @Override
    public String getName() {
        return "messages.getDialogs";
    }

    public int getCount() {
        return count;
    }
}
