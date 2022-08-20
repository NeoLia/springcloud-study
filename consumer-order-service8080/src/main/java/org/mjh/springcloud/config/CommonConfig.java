package org.mjh.springcloud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author: Neo Lia Marx
 * @date: 2021/12/24 22:38
 */
@Configuration
public class CommonConfig {
    private final RestTemplateBuilder restTemplateBuilder;

    @Autowired
    public CommonConfig(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return restTemplateBuilder.build();
    }
}
