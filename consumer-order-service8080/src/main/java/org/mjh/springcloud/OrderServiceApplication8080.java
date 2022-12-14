package org.mjh.springcloud;

import org.mjh.rule.MyRibbonRule;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: Neo Lia Marx
 * @date: 2021/12/24 21:47
 */
@SpringBootApplication
@MapperScan(basePackages = "org.mjh.springcloud.dao")
@EnableEurekaClient
@EnableFeignClients
@RibbonClient(name = "PROVIDER-PAYMENT-SERVICE", configuration = MyRibbonRule.class)
@EnableCircuitBreaker
public class OrderServiceApplication8080 {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication8080.class, args);
    }
}
