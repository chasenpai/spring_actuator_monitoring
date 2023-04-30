package com.monitoring.controller;


import com.monitoring.response.ProductResource;
import com.monitoring.service.ProductService;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/api/example", produces = MediaTypes.HAL_JSON_VALUE)
public class ProductController {

    private final ProductService productService;
    private final MeterRegistry meterRegistry;

    @GetMapping("/v1/products/{id}")
    public ProductResource getProduct(@PathVariable("id") Long id){
        /**
         * 사용자 정의 메트릭
         * /monitor/metrics/product-monitoring - 메트릭이 product-monitoring 인 모든 method 태그를 출력
         * 특정 태그에 대한 메트릭 출력 시 엔드포인트 URL 에 매개변수를 추가
         * /monitor/metrics/product-monitoring?tag=method:get product
         */
        Counter.builder("product-monitoring")
                .tag("method", "get product")
                .description("get product")
                .register(meterRegistry).increment();
        return new ProductResource(productService.getProduct(id));
    }

    @Counted("product-monitoring-2") //Counted Aspect - result, exception, method, class 등의 태그를 자동 적용
    @GetMapping("/v1/products")
    public List<ProductResource> getAllProducts(){
        return productService.getAllProducts().stream()
                .map(ProductResource::new)
                .collect(Collectors.toList());
    }

    /**
     * 주요 엔드포인트
     * beans - 스프링 컨테이너에 등록된 스프링 빈 표시
     * conditions - condition 을 통해서 빈을 등록할 때 평가 조건과 일치하거나 일치하지 않는 이유를 표시
     * configprops - @ConfigurationProperties 를 표시
     * env - Environment 정보를 표시 > info. 으로 시작하는 정보를 출력
     * health - 애플리케이션 헬스 정보 표시
     * httpexchanges - HTTP 호출 응답 정보를 표시 > 별도로 HttpExchangeRepository 구현 빈 등록 필요
     * info - 애플리케이션 정보 표시
     * loggers - 애플리케이션 로거 설정 표시 > 실시간 변경 가능
     * metrics - 애플리케이션 메트릭 정보를 표시
     * mappings - @RequestMapping 정보릂 표시
     * threaddump - 쓰레드 덤프 실행 및 표시
     * shutdown - 애플리케이션을 종료 > 기본적으로 비활성화
     */

    /**
     * 애플리케이션 종료 시키기
     * - /monitor/shutdown + Post 요청
     */

    /**
     * /monitor/loggers/com.monitoring.controller - 특정 로거
     * 실시간 로그 레벨 변경 가능 - 특정 로거 url + post 요청 + json
     * {
     *    "configuredLevel": "TRACE"
     * }
     */

    /**
     * 메트릭 Tag 필터
     * ex) /monitor/metrics/http.server.requests?tag=uri:/api/example/v1/products?tag=status:200
     */

}
