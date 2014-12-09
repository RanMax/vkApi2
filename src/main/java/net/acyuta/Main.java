package net.acyuta;

import net.acyuta.utils.C;
import net.acyuta.vk.Vk;
import net.acyuta.vk.VkLogic;

import java.io.IOException;

/**
 * Created by acyuta on 05.12.14.
 */
public class Main {

    public static void main(String[] args) {
        Vk vk = null;
        try {
            vk = Vk.init();
        } catch (IOException e) {
            C.die("Ошибка инициализации модуля VK");
        }
        assert vk != null;

        if (!vk.checkAccess())
            C.die(vk.usage());

        VkLogic logic = new VkLogic(vk);
        logic.dailyInfo();
    }
}
