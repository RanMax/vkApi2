package net.acyuta.vk;

import net.acyuta.utils.C;
import net.acyuta.vk.api.account.GetCounters;
import net.acyuta.vk.api.account.GetProfileInfo;

/**
 * Created by acyuta on 09.12.14.
 */
public class VkLogic {

    private Vk vk;

    public VkLogic(Vk vk) {
        this.vk = vk;
    }

    public void dailyInfo() {
        GetCounters method = (GetCounters) new GetCounters().execute();
        C.pn("---- Vk Stats ----");
        C.pn("Непрочитанных сообщений: " + method.get("messages"));
    }
}
