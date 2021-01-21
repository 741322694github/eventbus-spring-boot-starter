package com.github.zht.service;

import com.github.zht.eventbus.annotation.EventBusListener;
import com.github.zht.eventbus.enums.EventBusMode;
import com.github.zht.eventbus.event.BaseEvent;
import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Author zht
 * @Description
 * @Date 2021/1/18
 */
@EventBusListener
@Service
public class EventBusService {
    private static final Logger log = LoggerFactory.getLogger(EventBusService.class);

    @Subscribe
    public void subscribe(BaseEvent event){
        log.info("{} 处理事件信息 {}",getClass(),event.toString());
        Integer.valueOf("a");
    }

}
