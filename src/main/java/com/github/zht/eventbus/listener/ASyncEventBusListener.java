package com.github.zht.eventbus.listener;

import com.github.zht.eventbus.factory.DefaultEventBusFactory;
import com.github.zht.eventbus.factory.EventBusFactory;

import javax.annotation.PostConstruct;

/**
 * @Author zht
 * @Description 异步事件总线监听接口
 * @Date 2021/1/15
 */
public interface ASyncEventBusListener {

    @PostConstruct
    default void register(EventBusFactory eventBusFactory){
        // 注册当前实现类对象 this
        eventBusFactory.asyncRegister(this);
    }
}
