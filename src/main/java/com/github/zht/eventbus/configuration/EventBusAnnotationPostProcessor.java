package com.github.zht.eventbus.configuration;

import com.github.zht.eventbus.annotation.EventBusListener;
import com.github.zht.eventbus.enums.EventBusMode;
import com.github.zht.eventbus.support.EventBusSupport;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.util.Arrays;

/**
 * @Author zht
 * @Description
 * @Date 2021/1/18
 */
public class EventBusAnnotationPostProcessor implements BeanPostProcessor {

    private final EventBusSupport eventBusSupport;

    public EventBusAnnotationPostProcessor(EventBusSupport eventBusFactory) {
        this.eventBusSupport = eventBusFactory;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> aClass = AopProxyUtils.ultimateTargetClass(bean);
        if(aClass.isAnnotationPresent(EventBusListener.class)){
            EventBusListener busListener = bean.getClass().getAnnotation(EventBusListener.class);
            if(EventBusMode.SYNC.equals(busListener.mode())){
                eventBusSupport.register(bean);
            }else {
                eventBusSupport.asyncRegister(bean);
            }
        }else {
            Class<?>[] interfaces = aClass.getInterfaces();
            Arrays.stream(interfaces).filter(c -> "com.github.zht.eventbus.listener.EventBusListener".equals(c.getName()))
                    .findFirst().ifPresent(c -> {
                eventBusSupport.register(bean);
            });
            Arrays.stream(interfaces).filter(c -> "com.github.zht.eventbus.listener.AsyncEventBusListener".equals(c.getName()))
                    .findFirst().ifPresent(c -> {
                eventBusSupport.asyncRegister(bean);
            });
        }
        return bean;
    }
}
