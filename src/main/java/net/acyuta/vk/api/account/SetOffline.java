package net.acyuta.vk.api.account;

import com.google.gson.JsonObject;
import net.acyuta.vk.api.AbstractVkMethod;
import org.apache.http.NameValuePair;

import java.util.List;

/**
 * Помечает текущего пользователя как offline.
 */
public class SetOffline extends AbstractVkMethod {

    boolean success = false;

    @Override
    public boolean recognize(JsonObject response) {
        if (response.getAsInt() == 1)
            this.success = true;
        return true;
    }

    @Override
    public List<NameValuePair> getArgs() {
        return null;
    }

    @Override
    public String getName() {
        return "account.setOffline";
    }


    public boolean isSuccess() {
        return success;
    }
}
