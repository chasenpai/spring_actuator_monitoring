package com.monitoring.dto;

import com.monitoring.entity.Product;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductDto {

    private long id;

    private String name;

    private int price;

    private int stock;

    private LocalDateTime createDate;

    private LocalDateTime lastModifiedDate;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.stock = product.getStock();
        this.createDate = product.getCreatedDate();
        this.lastModifiedDate = product.getLastModifiedDate();
    }

}
