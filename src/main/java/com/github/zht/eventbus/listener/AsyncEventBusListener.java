package com.github.zht.eventbus.listener;

import com.github.zht.eventbus.event.BaseEvent;
import com.google.common.eventbus.Subscribe;

/**
 * @Author zht
 * @Description 异步事件总线监听接口
 * @Date 2021/1/15
 */
public interface AsyncEventBusListener<T extends BaseEvent> {

    @Subscribe
    void subscribe(T event);
}
