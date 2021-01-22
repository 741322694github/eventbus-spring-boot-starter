package com.github.zht.eventbus.support;

import com.github.zht.eventbus.event.BaseEvent;

/**
 * @Author zht
 * @Description
 * @Date 2021/1/18
 */
public class EventBusTemplate<T extends BaseEvent> {

    private EventBusSupport eventBusSupport;

    public EventBusTemplate(EventBusSupport eventBusSupport) {
        this.eventBusSupport = eventBusSupport;
    }

    public void post(T event){
        eventBusSupport.post(event);
    }


    public void asyncPost(T event){
        eventBusSupport.asyncPost(event);
    }
}
