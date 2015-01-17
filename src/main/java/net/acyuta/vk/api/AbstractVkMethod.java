package net.acyuta.vk.api;

import com.google.gson.JsonObject;
import net.acyuta.utils.C;
import net.acyuta.vk.Handler;
import net.acyuta.vk.Vk;
import net.acyuta.vk.exceptions.ErrorResponse;
import net.acyuta.vk.exceptions.UnknownResponse;
import net.acyuta.vk.exceptions.UnrecognizedResponse;
import org.apache.http.NameValuePair;

import java.util.List;

/**
 * Created by acyuta on 09.12.14.
 */
public abstract class AbstractVkMethod implements VkMethod, RecognizeJson {
    private static Handler handler = Vk.getImplements().getHandler();

    @Override
    public VkMethod execute() throws UnrecognizedResponse {
        JsonObject obj = null;
        try {
            obj = handler.start(this, getArgs());
        } catch (ErrorResponse response) {
            C.die(response);
        } catch (UnknownResponse response) {
            C.die("Неизвестный ответ от сервера");
        }
        if (obj != null && recognize(obj))
            return this;
        else throw new UnrecognizedResponse("Запрос к Api '".concat(getName()).concat("' не был распознан"));
    }

    abstract public List<NameValuePair> getArgs();
}
