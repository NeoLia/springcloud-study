package org.mjh.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author: Neo Lia Marx
 * @date: 2021/12/24 21:47
 */
@SpringBootApplication
@MapperScan(basePackages = "org.mjh.springcloud.dao")
@EnableEurekaClient
@EnableCircuitBreaker
public class PaymentService9081Application {
    public static void main(String[] args) {
        SpringApplication.run(PaymentService9081Application.class, args);
    }
}
