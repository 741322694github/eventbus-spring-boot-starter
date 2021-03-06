package com.github.zht.eventbus.listener;

import com.github.zht.eventbus.event.BaseEvent;
import com.google.common.eventbus.Subscribe;

/**
 * @Author zht
 * @Description 事件总线监听接口
 * @Date 2021/1/15
 */
public interface EventBusListener<T extends BaseEvent> {

    @Subscribe
    void subscribe(T event);
}
