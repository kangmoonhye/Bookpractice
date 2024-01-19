package com.example.demo.product.service;

import com.example.demo.product.model.Product;
import com.example.demo.product.model.dto.ProductDto;
import com.example.demo.product.model.dto.ProductRes;
import com.example.demo.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public void create(ProductDto productDto){

        productRepository.save(Product.builder()
                .idx(productDto.getIdx())
                .name(productDto.getName())
                .publisher(productDto.getPublisher())
                .author(productDto.getAuthor())
                .price(productDto.getPrice())
                .genre(productDto.getGenre())
                .build());
    }

    public void read(){

    }
    public ProductRes list(){

        List<Product> result = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();

        for(ProductDto productDto: result){
            ProductRes productRes = Product.builder()
                    .idx(product.getIdx())
                    .author(product.getAuthor())
                    .genre(product.getGenre())
                    .name(product.getName())
                    .price(product.getPrice())
                    .build();
        }

        return ProductRes.builder()
                .code(1000)
                .message("주문 불러오기에 성공 하였습니다")
                .isSuccess(true)
                .success(true)
                .result()
                .build();
    }
    public void update(){

    }

    public void delte(Integer idx){
        productRepository.delete(Product.builder()
                .idx(idx)
                .build());


    }




}
