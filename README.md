# eventbus-spring-boot-starter
基于Spring Boot 2.2.6 + Google Guava 事件总线的 spring-boot-starter 组件，对 Guava Event 做了封装，支持同步事件、异步事件、自定义异常回调钩子，让你发布、订阅事件更简单方便。

## 特性
- 使用注解订阅事件
- 事件发布支持同步事件和异步事件、订阅时决定同步订阅还是异步订阅
- 支持订阅事件异常时统一处理或回调处理

## 发布事件

    BaseEvent baseEvent = new BaseEvent();
    baseEvent.setCallback(this::point);
    baseEvent.setContent("this is eventbus test");
    EventBusTemplate.post(baseEvent);
    
    
## 订阅事件
订阅事件分为两种方式，一种是采用注解的方式，另外一种是采用编码实现接口的方式

####一、注解的方式

1. 类添加注解：@EventBusListener 可选择监听模式(同步/异步)
2. 方法添加注解：@Subscribe

默认订阅同步事件，如果要订阅异步事件，那么类的注解指定参数mode：@EventBusListener(mode = EventBusMode.ASYNC)

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
    
####二、接口实现的方式

同步事件
    
    @Service
    public class EventBusServiceImpl implements EventBusListener<BaseEvent> {
        private static final Logger log = LoggerFactory.getLogger(EventBusServiceImpl.class);
    
        @Override
        public void subscribe(BaseEvent event) {
            log.info("{} 处理事件信息 {}",getClass(),event.toString());
        }
    }    
    
异步事件

    @Service
    public class AsyncEventBusServiceImpl implements AsyncEventBusListener<BaseEvent> {
       private static final Logger log = LoggerFactory.getLogger(AsyncEventBusServiceImpl.class);
   
       @Override
       public void subscribe(BaseEvent event) {
           log.info("{} 异步处理事件信息 {}",getClass(),event.toString());
       }
    }    
    