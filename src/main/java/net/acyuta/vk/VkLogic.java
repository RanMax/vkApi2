package net.acyuta.vk;

import net.acyuta.utils.C;
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
        GetProfileInfo method = new GetProfileInfo();
        method.execute();
        C.pn(method);
    }
}
