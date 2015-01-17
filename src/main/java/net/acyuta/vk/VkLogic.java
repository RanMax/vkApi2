package net.acyuta.vk;

import net.acyuta.utils.C;
import net.acyuta.vk.api.account.GetCounters;
import net.acyuta.vk.api.account.util.Counters;
import net.acyuta.vk.api.messages.GetDialogs;

/**
 * Created by acyuta on 09.12.14.
 */
public class VkLogic {

    private Vk vk;

    public VkLogic(Vk vk) {
        this.vk = vk;
    }

    /**
     * Сводная информация по новым данным VK
     */
    public void dailyInfo() {
        GetCounters method = (GetCounters) new GetCounters().execute();
        if (method.hasNew()) {
            if (method.has(Counters.messages))
                C.pn("Непрочитанных сообщений: " + method.get(Counters.messages));
            if (method.has(Counters.events))
                C.pn("Новых событий: " + method.get(Counters.events));
            if (method.has(Counters.friends))
                C.pn("Запросов в друзья: " + method.get(Counters.friends));
            if (method.has(Counters.notifications))
                C.pn("Новых уведомлений: " + method.get(Counters.notifications));
            if (method.has(Counters.photos))
                C.pn("Новых фоток: " + method.get(Counters.photos));
        } else {
            C.pn("Ничего нового :(");
        }

    }

    public void printUnread() {
        C.pn("Dummy Unread Messages");
        GetDialogs dialogs = new GetDialogs();
        dialogs.execute();
    }
}
