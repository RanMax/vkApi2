package net.acyuta.vk.api.account.util;

/**
 * Created by acyuta on 09.12.14.
 */
public enum Counters {
    friends, messages, photos, videos, notes, gifts, events, groups, notifications, sdk;

    public static boolean has(String name) {
        for (Counters c : values())
            if (c.name().equals(name)) return true;
        return false;
    }
}
