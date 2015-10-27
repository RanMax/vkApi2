package net.acyuta.vk.api;

import com.google.gson.JsonObject;
import com.sun.istack.internal.NotNull;
import net.acyuta.utils.C;
import net.acyuta.vk.Handler;
import net.acyuta.vk.Vk;
import net.acyuta.vk.exceptions.ErrorResponse;
import net.acyuta.vk.exceptions.UnknownResponse;
import net.acyuta.vk.exceptions.UnrecognizedResponse;
import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by acyuta on 09.12.14.
 */
public abstract class AbstractVkMethod implements VkMethod, RecognizeJson {
    private static Handler handler = Vk.getImplements().getHandler();
    private ArrayList<NameValuePair> args;

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

    public List<NameValuePair> getArgs() {
        return this.args;
    }

    public void setArgs(@NotNull Collection<NameValuePair> args) {
        if (this.args == null)
            this.args = new ArrayList<NameValuePair>();
        else this.args.clear();
        this.args.addAll(args);
    }

    public void putArg(NameValuePair arg) {
        for (NameValuePair pair : args)
            if (pair.getName().equals(arg.getName())) {
                args.remove(pair);
                break;
            }
        args.add(arg);
        //C.pn("Аргумент '" + arg.getName() + "' установлен " + arg.getValue());
    }
}
