package com.github.zht.service;

import com.github.zht.eventbus.annotation.EventBusListener;
import com.google.common.eventbus.Subscribe;
import org.springframework.stereotype.Service;

/**
 * @Author zht
 * @Description
 * @Date 2021/1/18
 */
@EventBusListener
@Service
public class EventbusService{

    @Subscribe
    public void test(Object event){
        System.out.println(getClass()+" : "+event);
        Integer.valueOf("a");
    }

}
