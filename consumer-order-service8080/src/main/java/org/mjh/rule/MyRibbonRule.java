package org.mjh.rule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Neo Lia Marx
 * @date: 2022/3/11 16:07
 */
@Configuration("myRibbonRule")
public class MyRibbonRule {
    @Bean
    public IRule myRibbonRandomRule() {
        return new RandomRule();
    }
}
