package com.monitoring.config;

import org.springframework.boot.actuate.web.exchanges.InMemoryHttpExchangeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpExchangeConfig {

    /**
     * http exchanges 엔드포인트 등록
     * - http 요청&응답의 과거 기록 확인 가능
     */
    @Bean
    public InMemoryHttpExchangeRepository httpExchangeRepository() {
        InMemoryHttpExchangeRepository repository = new InMemoryHttpExchangeRepository();
        repository.setCapacity(200); //기본적으로 100개의 요청을 제공하지만, 최대 요청 수 변경 가능
        return new InMemoryHttpExchangeRepository();
    }

}
