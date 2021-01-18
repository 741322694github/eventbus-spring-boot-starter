package com.github.zht.service;

import com.github.zht.eventbus.event.BaseEvent;
import com.github.zht.eventbus.factory.EventBusTemplate;
import org.junit.jupiter.api.Test;

/**
 * @Author zht
 * @Description
 * @Date 2021/1/18
 */
public class EventBusTest extends BaseTest {

    @Test
    public void post(){
        BaseEvent baseEvent = new BaseEvent();
        baseEvent.setCallback(this::point);
        baseEvent.setContent("this is eventbus test");
        EventBusTemplate.post(baseEvent);
    }

    public Class<Void> point(BaseEvent baseEvent,Throwable throwable){
        throwable.printStackTrace();
        System.out.println("event id "+baseEvent.getId()+" error");
        return Void.TYPE;
    }
}
