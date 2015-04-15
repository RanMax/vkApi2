package net.acyuta.vk;

import net.acyuta.utils.C;
import net.acyuta.vk.api.account.GetCounters;
import net.acyuta.vk.api.account.util.Counters;
import net.acyuta.vk.api.messages.GetDialogs;
import net.acyuta.vk.api.messages.GetMultidialogList;
import net.acyuta.vk.api.messages.utils.ChatDialog;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.Arrays;
import java.util.Map;

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

    public Map<Integer,ChatDialog> getAllMultidialogs() {
        int step = 200;
        C.pn("All Multi Dialogs");
        GetMultidialogList multidialogList = new GetMultidialogList();
        multidialogList.setArgs(Arrays.asList(
                (NameValuePair) new BasicNameValuePair("count", String.valueOf(step)),
                (NameValuePair) new BasicNameValuePair("preview_length", "50")
        ));
        multidialogList.execute();
        int count = multidialogList.getCount();
        int i = count;
        int j = 1;

        while (i > step) {
            i = i - step;
            multidialogList.putArg(new BasicNameValuePair("offset", String.valueOf(count - i)));
            C.pn("Смещение " + (count - i) + " - " + multidialogList.getArgs().toString());
            if (j % 3 == 0) try {
                C.pn("Ожидание...");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            j++;
            multidialogList.execute();
        }
        C.pn(multidialogList.getSet());
        return multidialogList.getSet();

    }

}
