package com.github.zht.service;

import com.github.zht.eventbus.event.BaseEvent;
import com.github.zht.eventbus.support.EventBusTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * @Author zht
 * @Description
 * @Date 2021/1/18
 */
public class EventBusTest extends BaseTest {

    @Autowired
    private EventBusTemplate<BaseEvent> eventBusTemplate;

    @Test
    public void post(){
        BaseEvent baseEvent = new BaseEvent();
//        baseEvent.setCallback(this::point);
        baseEvent.setContent("this is eventbus test");
        eventBusTemplate.post(baseEvent);
    }

    @Test
    public void asyncPost(){
        BaseEvent baseEvent = new BaseEvent();
        baseEvent.setCallback(this::failCallback);
        baseEvent.setContent("this is async eventbus test");
        eventBusTemplate.asyncPost(baseEvent);
    }

    public Class<Void> failCallback(BaseEvent baseEvent,Throwable throwable){
        throwable.printStackTrace();
        System.out.println("event id "+baseEvent.getId()+" error");
        return Void.TYPE;
    }
}
