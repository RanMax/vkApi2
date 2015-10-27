package net.acyuta.vk.api.account;

import com.google.gson.JsonObject;
import net.acyuta.vk.api.AbstractVkMethod;
import net.acyuta.vk.api.account.util.Counters;
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
    boolean clear = false;

    @Override
    public boolean recognize(JsonObject response) {
        if (response == null)
            return false;
        for (Counters a : Counters.values()) {
            if (response.has(a.name())) {
                clear = true;
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

    public boolean hasNew() {
        return clear;
    }

    public int get(Counters counter) {
        if (has(counter))
            return map.get(counter.name());
        else
            return 0;
    }

    public int get(String name) {
        if (!Counters.has(name))
            return -1;
        else
            return get(Counters.valueOf(name));
    }


    public boolean has(Counters counters) {
        return map.containsKey(counters.name());
    }
}
