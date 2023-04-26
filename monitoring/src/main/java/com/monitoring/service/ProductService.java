package com.monitoring.service;

import com.monitoring.dto.ProductDto;
import com.monitoring.entity.Product;
import com.monitoring.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    public ProductDto getProduct(Long id){

        Optional<Product> product = productRepository.findById(id);

        if(product.isPresent()){
            return new ProductDto(product.get());
        }else{
            throw new RuntimeException("");
            //throw new ProductNotFoundException(ErrorCode.PRODUCT_NOT_FOUND);
        }

    }

}
