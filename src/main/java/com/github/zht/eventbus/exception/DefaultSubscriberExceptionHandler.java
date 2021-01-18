package com.github.zht.eventbus.exception;

import com.github.zht.eventbus.event.BaseEvent;
import com.google.common.eventbus.SubscriberExceptionContext;
import com.google.common.eventbus.SubscriberExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author zht
 * @Description
 * @Date 2021/1/18
 */
public class DefaultSubscriberExceptionHandler implements SubscriberExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(DefaultSubscriberExceptionHandler.class);

    @Override
    public void handleException(Throwable throwable, SubscriberExceptionContext subscriberExceptionContext) {
        BaseEvent event = (BaseEvent)subscriberExceptionContext.getEvent();
        log.error("Event id "+event.getId()+" execute fail by "+throwable.getMessage());
    }
}
