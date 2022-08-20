package org.mjh.springcloud.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Neo Lia Marx
 * @date: 2022/3/11 16:07
 */
@Configuration("myRibbonConfig")
public class RibbonConfig {
    @Bean
    public IRule myRibbonRule() {
        return new RandomRule();
    }
}
