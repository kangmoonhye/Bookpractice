package com.example.demo.product.service;

import com.example.demo.product.model.Product;
import com.example.demo.product.model.dto.ProductDto;
import com.example.demo.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public void list(Integer idx){

    }
    public void update(){

    }

    public void delte(Integer idx){
        productRepository.delete(Product.builder()
                .idx(idx)
                .build());


    }




}
