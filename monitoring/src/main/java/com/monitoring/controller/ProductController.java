package com.monitoring.controller;

import com.monitoring.dto.ProductDto;
import com.monitoring.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/api/example", produces = MediaTypes.HAL_JSON_VALUE)
public class ProductController {

    private final ProductService productService;

    @GetMapping("/v1/products/{id}")
    public EntityModel<ProductDto> getProduct(@PathVariable("id") Long id){
        return EntityModel.of(
                productService.getProduct(id),
                linkTo(methodOn(ProductController.class).getProduct(id)).withSelfRel()
                //linkTo(methodOn(ProductController.class).getAllProducts()).withRel("list")
        );
    }

}
