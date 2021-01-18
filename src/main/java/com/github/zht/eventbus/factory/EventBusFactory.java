package com.github.zht.eventbus.factory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @Author zht
 * @Description
 * @Date 2021/1/18
 */
public interface EventBusFactory extends DisposableBean, InitializingBean {

    void register(Object listener);

    void asyncRegister(Object listener);

    void unregister(Object listener);

    void asyncUnRegister(Object listener);
}
