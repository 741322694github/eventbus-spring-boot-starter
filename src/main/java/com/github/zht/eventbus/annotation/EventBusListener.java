package com.github.zht.eventbus.annotation;

import com.github.zht.eventbus.enums.EventBusMode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * @Author zht
 * @Description 事件总线监听注解
 * @Date 2021/1/15
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface EventBusListener {

    EventBusMode mode() default EventBusMode.SYNC;

}
