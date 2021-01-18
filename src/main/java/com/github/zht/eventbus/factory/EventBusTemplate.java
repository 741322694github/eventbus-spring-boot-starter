package com.github.zht.eventbus.factory;

/**
 * @Author zht
 * @Description
 * @Date 2021/1/18
 */
public class EventBusTemplate {


    public static void post(Object event){
        DefaultEventBusFactory.post(event);
    }


    public static void asyncPost(Object event){
        DefaultEventBusFactory.asyncPost(event);
    }
}
