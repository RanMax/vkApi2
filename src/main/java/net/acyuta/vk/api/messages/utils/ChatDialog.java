package net.acyuta.vk.api.messages.utils;

/**
 * Created by acyuta on 18.01.15.
 */
public class ChatDialog {
    public String chat_id;
    public String title;
    public String body;
    public String date;
    public String user_id;

    public ChatDialog(String chat_id, String title, String body, String date, String user_id) {
        this.chat_id = chat_id;
        this.title = title;
        this.body = body;
        this.date = date;
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "ChatDialog{" +
                "chat_id='" + chat_id + '\'' +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", date='" + date + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }
}
