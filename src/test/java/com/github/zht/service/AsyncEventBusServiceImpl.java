package com.github.zht.service;

import com.github.zht.eventbus.event.BaseEvent;
import com.github.zht.eventbus.listener.AsyncEventBusListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Author zht
 * @Description
 * @Date 2021/1/20
 */

@Service
public class AsyncEventBusServiceImpl implements AsyncEventBusListener<BaseEvent> {
    private static final Logger log = LoggerFactory.getLogger(AsyncEventBusServiceImpl.class);

    @Override
    public void subscribe(BaseEvent event) {
        log.info("{} 异步处理事件信息 {}",getClass(),event.toString());
    }
}

