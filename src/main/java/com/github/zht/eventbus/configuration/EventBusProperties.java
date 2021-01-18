package com.github.zht.eventbus.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author zht
 * @Description
 * @Date 2021/1/18
 */
@ConfigurationProperties(prefix = "event-bus.executor")
public class EventBusProperties {

   private Integer corePoolSize = Runtime.getRuntime().availableProcessors();
   private Integer maximumPoolSize = corePoolSize * 2;
   private Integer keepAliveTime = 30000;
   private Integer queueSize = 100;
   private String  exceptionHandler;

    public Integer getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(Integer corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public Integer getMaximumPoolSize() {
        return maximumPoolSize;
    }

    public void setMaximumPoolSize(Integer maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }

    public Integer getKeepAliveTime() {
        return keepAliveTime;
    }

    public void setKeepAliveTime(Integer keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

    public Integer getQueueSize() {
        return queueSize;
    }

    public void setQueueSize(Integer queueSize) {
        this.queueSize = queueSize;
    }

    public String getExceptionHandler() {
        return exceptionHandler;
    }

    public void setExceptionHandler(String exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
    }
}
