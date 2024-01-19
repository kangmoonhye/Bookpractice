package com.example.demo.product.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
@Data
public class ProductRes {
    Integer code;
    String message;
    Boolean success;
    Boolean isSuccess;
    ProductDto result;

}
