package net.acyuta.vk;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import net.acyuta.vk.api.VkMethod;
import net.acyuta.vk.exceptions.ErrorResponse;
import net.acyuta.vk.exceptions.UnknownResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by acyuta on 08.12.14.
 */
public class Handler {
    Vk parent = null;

    public Handler(Vk parent) {
        this.parent = parent;
    }

    public JsonObject start(VkMethod method, List<NameValuePair> arguments) throws ErrorResponse, UnknownResponse {
        String answer = request(method, arguments);
        //C.pn("Получено с сервера: ".concat(answer));
        JsonObject jsonObject = new JsonParser().parse(answer).getAsJsonObject();
        if (jsonObject.has("response")) {
            JsonElement element = jsonObject.get("response");
            if (element.isJsonNull() || (element.isJsonArray() && element.getAsJsonArray().size() == 0))
                return new JsonObject();
            else return jsonObject.getAsJsonObject("response");
        }
        if (jsonObject.has("error"))
            throw new ErrorResponse("Запрос вернулся с ошибкой", jsonObject.getAsJsonObject("error"));
        else
            throw new UnknownResponse("Непонятный ответ от сервера: ".concat(jsonObject.getAsString()), jsonObject);

    }

    protected String request(@NotNull VkMethod method, @Nullable List<NameValuePair> arguments) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost(parent.getMethod_url()
                .concat("method/")
                .concat(method.getName())
                .concat("?access_token=")
                .concat(parent.getToken())
                .concat("&v=")
                .concat(parent.getVersion())
        );

        try {
            if (arguments != null)
                post.setEntity(new UrlEncodedFormEntity(arguments));
            CloseableHttpResponse response = httpClient.execute(post);
            BufferedInputStream stream = new BufferedInputStream(response.getEntity().getContent());
            Scanner scanner = new Scanner(stream);
            StringBuilder sb = new StringBuilder();
            while (scanner.hasNextLine())
                sb.append(scanner.nextLine());
            return sb.toString();

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
