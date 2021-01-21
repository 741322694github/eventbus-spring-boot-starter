package com.github.zht.eventbus.configuration;

import com.github.zht.eventbus.factory.EventBusFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author zht
 * @Description
 * @Date 2021/1/18
 */
@Configuration
@EnableConfigurationProperties(EventBusProperties.class)
public class EventBusConfiguration {

    @Bean
    public EventBusAnnotationPostProcessor initEventBusAnnotationPostProcessor(EventBusFactory eventBusFactory){
        return new EventBusAnnotationPostProcessor(eventBusFactory);
    }
}
