package com.github.zht.eventbus.support;

import com.github.zht.eventbus.configuration.EventBusProperties;
import com.github.zht.eventbus.event.BaseEvent;
import com.github.zht.eventbus.exception.DefaultSubscriberExceptionHandler;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.SubscriberExceptionContext;
import com.google.common.eventbus.SubscriberExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
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
public class EventBusSupport implements DisposableBean, InitializingBean {
    private static final Logger log = LoggerFactory.getLogger(EventBusSupport.class);

    private final EventBusProperties eventBusProperties;

    private static Executor executor;
    private static EventBus eventBus;
    private static AsyncEventBus asyncEventBus;

    public EventBusSupport(EventBusProperties eventBusProperties) {
        this.eventBusProperties = eventBusProperties;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("EventBus init...");
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
                    BaseEvent event = (BaseEvent)subscriberExceptionContext.getEvent();
                    if(event != null && event.getCallback() != null){
                        event.getCallback().apply(event,throwable);
                    }else {
                        DefaultSubscriberExceptionHandler defaultSubscriberExceptionHandler = new DefaultSubscriberExceptionHandler();
                        defaultSubscriberExceptionHandler.handleException(throwable, subscriberExceptionContext);
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

    public void register(Object listener){
        eventBus.register(listener);
    }
    public void asyncRegister(Object listener){
        asyncEventBus.register(listener);
    }
    public void unregister(Object listener){
        eventBus.unregister(listener);
    }
    public void asyncUnRegister(Object listener){
        asyncEventBus.unregister(listener);
    }

    public void post(BaseEvent event){
        eventBus.post(event);
    }

    public void asyncPost(BaseEvent event){
        asyncEventBus.post(event);
    }
}
