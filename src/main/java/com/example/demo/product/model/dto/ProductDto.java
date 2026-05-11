package com.example.demo.product.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {

    private Integer idx;
    private String productName;
    private Integer price;
    private String salesLink;
    private String reviewLink;
    private String imagePath;
}