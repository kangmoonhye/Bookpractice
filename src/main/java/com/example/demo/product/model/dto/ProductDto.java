package com.example.demo.product.model.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductDto {
    Integer idx;

    String name;
    String publisher;
    String author;
    Integer price;
    String genre;

}
