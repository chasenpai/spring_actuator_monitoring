package com.monitoring.response;

import com.monitoring.controller.ProductController;
import com.monitoring.dto.ProductDto;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class ProductResource extends EntityModel<ProductDto> {

    public ProductResource(ProductDto content){
        super(content);
        add(linkTo(methodOn(ProductController.class).getProduct(content.getId())).withSelfRel());
        add(linkTo(methodOn(ProductController.class).getAllProducts()).withRel("list"));
    }

}
