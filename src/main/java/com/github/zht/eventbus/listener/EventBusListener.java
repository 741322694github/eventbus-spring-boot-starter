package com.github.zht.eventbus.listener;

import com.github.zht.eventbus.factory.DefaultEventBusFactory;
import com.github.zht.eventbus.factory.EventBusFactory;

import javax.annotation.PostConstruct;

/**
 * @Author zht
 * @Description 事件总线监听接口
 * @Date 2021/1/15
 */
public interface EventBusListener {

    @PostConstruct
    default void register(EventBusFactory eventBusFactory){
        // 注册当前实现类对象 this
        eventBusFactory.register(this);
    }
}
