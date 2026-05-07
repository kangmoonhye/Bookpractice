package com.example.demo.product.model.dto;

import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

    private Integer idx;

    private String productName;

    private Integer price;

    @Column(columnDefinition = "TEXT")
    private String salesLink;

    @Column(columnDefinition = "TEXT")
    private String reviewLink;

    private String imagePath;
}