package com.monitoring.service;

import com.monitoring.dto.ProductDto;
import com.monitoring.entity.Product;
import com.monitoring.exception.ProductNotFoundException;
import com.monitoring.model.ExceptionCode;
import com.monitoring.repository.ProductRepository;
import com.monitoring.request.ProductRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    public ProductDto getProduct(Long id){
        return productRepository.findById(id)
                .map(ProductDto::new)
                .orElseThrow(() -> new ProductNotFoundException(ExceptionCode.PRODUCT_NOT_FOUND));

    }

    public List<ProductDto> getAllProducts(){
        return productRepository.findAll()
                .stream()
                .map(ProductDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProductDto saveProduct(ProductRequest request){
        Product product = productRepository.save(new Product(request));
        return new ProductDto(product);
    }

    @Transactional
    public ProductDto updateProduct(ProductRequest request){
        Product product = productRepository.findById(request.getId())
                .orElseThrow(() -> new ProductNotFoundException(ExceptionCode.PRODUCT_NOT_FOUND));
        product.updateProduct(request);
        return new ProductDto(product);
    }

}
