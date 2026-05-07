package com.example.demo.product.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRes {

    private Integer code;
    private String message;
    private Boolean isSuccess;
    private Boolean success;
    private List<ProductDto> result;
}