package com.github.zht.eventbus.event;

import java.io.Serializable;
import java.util.function.BiFunction;

/**
 * @Author zht
 * @Description
 * @Date 2021/1/18
 */
public class BaseEvent implements Serializable {

    private String id;

    private String content;

    private BiFunction<BaseEvent,Throwable,Class<Void>> callback;

    public BaseEvent() {
        this.id = "eb"+System.currentTimeMillis();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BiFunction<BaseEvent, Throwable, Class<Void>> getCallback() {
        return callback;
    }

    public void setCallback(BiFunction<BaseEvent, Throwable, Class<Void>> callback) {
        this.callback = callback;
    }

    @Override
    public String toString() {
        return "BaseEvent{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", callback=" + callback +
                '}';
    }
}
