package net.acyuta.vk.api.account;

import com.google.gson.JsonObject;
import net.acyuta.vk.api.AbstractVkMethod;
import org.apache.http.NameValuePair;

import java.util.HashMap;
import java.util.List;

/**
 * Created by acyuta on 09.12.14.
 */
public class GetCounters extends AbstractVkMethod {

    HashMap<String, Integer> map = new HashMap<String, Integer>();
    /**
     * Флаг, получены ли новые данные
     */
    boolean clear = true;

    @Override
    protected boolean recognize(JsonObject response) {
        if (response == null)
            return false;
        for (Counters a : Counters.values()) {
            if (response.has(a.name())) {
                clear = false;
                map.put(a.name(), response.get(a.name()).getAsInt());
            }
        }
        return true;
    }

    @Override
    public List<NameValuePair> getArgs() {
        return null;
    }

    @Override
    public String getName() {
        return "account.getCounters";
    }

    @Override
    public boolean isNew() {
        return false;
    }

    public boolean hasNew() {
        return clear;
    }

    public int get(String name) {
        if (!Counters.has(name))
            return -1;
        else if (map.containsKey(name))
            return map.get(name);
        else
            return 0;
    }


}
