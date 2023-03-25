# 微服务学习
SpringCloud和SpringCloudAlibaba微服务分布式架构学习

---
## eureka

### 注册中心（eureka-server7001）
#### maven依赖
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

##### yml配置
```yaml
spring:
  application:
    name: eureka-server7001

eureka:
  instance:
    hostname: localhost
  client:
    register-with-eureka: false
    fetch-registry: false
    service-url:
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
```

#### 启动类注解
```java
//@EnableEurekaServer
```

### 客户端
#### maven依赖
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

#### yml配置
```yaml
eureka:
  instance:
    hostname: localhost
  client:
    register-with-eureka: false
    fetch-registry: false
    service-url:
      defaultZone: http://localhost:7001/eureka
```
#### 启动类注解
```java
//@EnableEurekaClient
```

---

### eureka的自我保护机制

Eureka服务端会检查最近15分钟内所有Eureka 实例正常心跳占比，如果低于85%就会触发自我保护机制。触发了保护机制，Eureka将暂时把这些失效的服务保护起来，不让其过期，但这些服务也并不是永远不会过期。Eureka在启动完成后，每隔60秒会检查一次服务健康状态，如果这些被保护起来失效的服务过一段时间后（默认90秒）还是没有恢复，就会把这些服务剔除。如果在此期间服务恢复了并且实例心跳占比高于85%时，就会自动关闭自我保护机制。

### 禁止自我保护
```yaml
eureka:
  server:
    enable-self-preservation: false
    eviction-interval-timer-in-ms: 2000
```

## springcloud-zookeeper

### maven依赖
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-zookeeper-discovery</artifactId>
</dependency>
```
### yml配置
```yaml
spring:
  application:
    name: zookeeper-provider-payment
  cloud:
    zookeeper:
      connect-string: 192.168.159.135:2181
```

### 启动类注解
```java
//@EnableDiscoveryClient
```

---

## springcloud-consul

### 下载安装地址
https://developer.hashicorp.com/consul/downloads

### 启动命令
consul agent -dev

### maven依赖
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-consul-discovery</artifactId>
</dependency>
```

### yml配置

```yaml
spring:
  application:
    name: consul-provider-payment
  cloud:
    consul:
      host: localhost
      port: 8500
```

## eureka、zookeeper、consul三个注册中心的异同点
![img.png](image/img.png)

![img_1.png](image/img_1.png)

---

## OpenFeign

### maven依赖
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```

### 客户端申明
```java
@FeignClient(value = "eureka-provider-payment")
public interface PaymentFeignService {

    @GetMapping("/payment/queryById/{id}")
    CommonResult<Payment> queryById(@PathVariable Integer id);

    @PostMapping("/payment/insert")
    CommonResult<Integer> insert(@RequestBody Payment payment);
}
```

### 启动类开启OpenFeign注解
```java
//@EnableFeignClients
```

### 超时控制
```yaml
#第一种方式直接使用底层的ribbon来控制超时
#ribbon:
#  ReadTimeout: 5000
#  ConnectTimeout: 5000

# 第二种使用feign的配置来实现
feign:
  client:
    config:
      # 默认配置，对所有的客户端都有作用
      default:
        connectTimeout: 5000
        readTimeout: 5000
      # 对单独的服务进行配置，优先级大于默认配置，也既是调用eureka-provider-payment时候，会使用单独配置
      eureka-provider-payment:
        connectTimeout: 2000
        readTimeout: 2000
```

### 日志使用
和上述超时配置一样是在FeignClientProperties中
```yaml
feign:
  client:
    config:
      # 默认配置，对所有的客户端都有作用
      default:
        connect-timeout: 5000
        read-timeout: 5000
        logger-level: FULL
      # 对单独的服务进行配置，优先级大于默认配置，也既是调用eureka-provider-payment时候，会使用单独配置
#      eureka-provider-payment:
#        connect-timeout: 2000
#        read-timeout: 2000
logging:
  level:
    com.fengfan.springcloud: debug
```
因为 OpenFeign 的调试日志是以 debug 级别来输出的。而 Spring Boot 默认的日志级别是 info 级别。info 级别是大于 debug 级别的，所以 debug 级别的日志（OpenFeign日志）不会输出。我们需要把我们项目日志改成debug。

---

## Hystrix

### 概述
![img.png](image/img_3.png)
![img.png](image/img_2.png)

### 重要概念

1. 服务降级： 服务器忙，请稍候再试，不让客户端等待并立刻返回一个友好提示，fallback。
哪些情况会触发降级：程序运行异常、超时、服务熔断触发服务降级、线程池/信号量打满也会导致服务降级

2. 服务熔断：类比保险丝达到最大服务访问后，直接拒绝访问，拉闸限电，然后调用服务降级的方法并返回友好提示。整个过程：服务的降级->进而熔断->恢复调用链路

3. 服务限流：秒杀高并发等操作，严禁一窝蜂的过来拥挤，大家排队，一秒钟N个，有序进行

### 服务降级

@HystrixCommand实现服务端服务降级

feign客户端降级：
```yaml
feign:
  hystrix:
    enabled: true
```

启动类：
```java
//@EnableHystrix
```

业务类：
```java
@FeignClient(value = "hystrix-provider-payment", fallback = HystrixFeignFallbackImpl.class)
public interface HystrixFeignConsumerService {

    @GetMapping("/hystrix/ok/{id}")
    String infoOk(@PathVariable String id);

    @GetMapping("/hystrix/timeOut/{id}")
    String timeOut(@PathVariable String id);
}
@Component
public class HystrixFeignFallbackImpl implements HystrixFeignConsumerService {
    @Override
    public String infoOk(String id) {
        return "infoOk fallback";
    }

    @Override
    public String timeOut(String id) {
        return "timeout fall back";
    }
}
```

### 服务熔断

```java
public class HystrixConsumerController {
    //服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),  //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),   //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),  //时间范围
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"), //失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if (id < 0){
            throw new RuntimeException("*****id 不能负数");
        }
        return Thread.currentThread().getName()+"\t"+"调用成功";
    }
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id 不能负数，请稍候再试,id: " +id;
    }
}

```

#### 断路器在什么情况下开始起作用
![img_4.png](image/img_4.png)

#### 断路器开启或者关闭的条件
1. 当满足一定阀值的时候（默认10秒内超过20个请求次数）
2. 当失败率达到一定的时候（默认10秒内超过50%请求失败）
3. 到达以上阀值，断路器将会开启
4. 当开启的时候，所有请求都不会进行转发
5. 一段时间之后（默认是5秒），这个时候断路器是半开状态，会让其中一个请求进行转发。如果成功，断路器会关闭，若失败，继续开启。重复4和5

#### 断路器打开之后
![img_5.png](image/img_5.png)

---
