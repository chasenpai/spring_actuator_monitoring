package com.monitoring.config;

import io.micrometer.core.aop.CountedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CountedAspectConfig {

    /**
     * CountedAspect 빈 등록 시 @Counted 를 인지해서 Counter AOP 를 적용
     */
    @Bean
    public CountedAspect countedAspect(MeterRegistry registry){
        return new CountedAspect(registry);
    }

}
