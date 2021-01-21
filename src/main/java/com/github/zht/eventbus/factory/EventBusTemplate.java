package com.github.zht.eventbus.factory;

import com.github.zht.eventbus.event.BaseEvent;

/**
 * @Author zht
 * @Description
 * @Date 2021/1/18
 */
public class EventBusTemplate {


    public static void post(BaseEvent event){
        DefaultEventBusFactory.post(event);
    }


    public static void asyncPost(BaseEvent event){
        DefaultEventBusFactory.asyncPost(event);
    }
}
