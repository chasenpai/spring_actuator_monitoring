package com.monitoring.controller;


import com.monitoring.response.ProductResource;
import com.monitoring.service.ProductService;
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

    @GetMapping("/v1/products")
    public List<ProductResource> getAllProducts(){
        Counter.builder("product-monitoring")
                .tag("method", "get product list")
                .description("get product list")
                .register(meterRegistry).increment();
        return productService.getAllProducts().stream()
                .map(ProductResource::new)
                .collect(Collectors.toList());
    }

}
