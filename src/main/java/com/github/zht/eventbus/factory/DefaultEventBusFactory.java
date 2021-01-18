package com.github.zht.eventbus.factory;

import com.github.zht.eventbus.configuration.EventBusProperties;
import com.github.zht.eventbus.event.BaseEvent;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.SubscriberExceptionContext;
import com.google.common.eventbus.SubscriberExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author zht
 * @Description
 * @Date 2021/1/18
 */
@Component
public class DefaultEventBusFactory implements EventBusFactory {
    private static final Logger log = LoggerFactory.getLogger(DefaultEventBusFactory.class);

    @Autowired
    private EventBusProperties eventBusProperties;

    private static Executor executor;
    private static EventBus eventBus;
    private static AsyncEventBus asyncEventBus;


    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("EventBus factory init...");
        executor = new ThreadPoolExecutor(eventBusProperties.getCorePoolSize(),
                eventBusProperties.getMaximumPoolSize(),
                eventBusProperties.getKeepAliveTime(),
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(eventBusProperties.getQueueSize()));
        SubscriberExceptionHandler handler;
        if(!StringUtils.isEmpty(eventBusProperties.getExceptionHandler())){
            handler = (SubscriberExceptionHandler) Class.forName(eventBusProperties.getExceptionHandler()).newInstance();
        }else {
            handler = new SubscriberExceptionHandler() {
                @Override
                public void handleException(Throwable throwable, SubscriberExceptionContext subscriberExceptionContext) {
                    // 这里可以埋个钩子,当出现异常时调用钩子
                    BaseEvent event = (BaseEvent)subscriberExceptionContext.getEvent();
                    if(event != null && event.getCallback() != null){
                        event.getCallback().apply(event,throwable);
                    }else {
                        log.error("Event id "+event.getId()+" execute fail by {}"+throwable);
                    }
                }
            };
        }
        eventBus = new EventBus(handler);
        asyncEventBus = new AsyncEventBus(executor,handler);
    }

    @Override
    public void destroy() throws Exception {
        log.info("EventBus executor shutdown...");
        ((ThreadPoolExecutor)executor).shutdownNow();
    }

    public EventBusProperties getEventBusProperties() {
        return eventBusProperties;
    }

    @Override
    public void register(Object listener){
        eventBus.register(listener);
    }

    @Override
    public void asyncRegister(Object listener){
        asyncEventBus.register(listener);
    }
    @Override
    public void unregister(Object listener){
        eventBus.unregister(listener);
    }
    @Override
    public void asyncUnRegister(Object listener){
        asyncEventBus.unregister(listener);
    }

    public static void post(Object event){
        eventBus.post(event);
    }

    public static void asyncPost(Object event){
        asyncEventBus.post(event);
    }

}
