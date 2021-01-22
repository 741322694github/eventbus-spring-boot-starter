package com.github.zht.eventbus.configuration;

import com.github.zht.eventbus.support.EventBusSupport;
import com.github.zht.eventbus.support.EventBusTemplate;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
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
@AutoConfigureAfter(EventBusProperties.class)
public class EventBusConfiguration {


    @Bean
    @ConditionalOnMissingBean
    public EventBusSupport eventBusSupport(EventBusProperties eventBusProperties){
        return new EventBusSupport(eventBusProperties);
    }

    @Bean
    @ConditionalOnClass(EventBusSupport.class)
    @ConditionalOnMissingBean
    public EventBusAnnotationPostProcessor initEventBusAnnotationPostProcessor(EventBusSupport eventBusSupport){
        return new EventBusAnnotationPostProcessor(eventBusSupport);
    }

    @Bean
    @ConditionalOnClass(EventBusSupport.class)
    @ConditionalOnMissingBean
    public EventBusTemplate<?> initEventBusTemplate(EventBusSupport eventBusSupport){
        return new EventBusTemplate<>(eventBusSupport);
    }

}
