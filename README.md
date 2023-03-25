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
