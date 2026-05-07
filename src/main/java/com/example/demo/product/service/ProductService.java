package com.example.demo.product.service;

import com.example.demo.product.model.Product;
import com.example.demo.product.model.dto.ProductDto;
import com.example.demo.product.model.dto.ProductRes;
import com.example.demo.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final GcsFileService gcsFileService;

    public void create(String productName,
                       Integer price,
                       String salesLink,
                       String reviewLink,
                       List<MultipartFile> images) throws IOException {

        List<String> imagePaths = new ArrayList<>();

        if (images != null) {
            for (MultipartFile file : images) {
                String path = gcsFileService.upload(file);
                if (path != null) {
                    imagePaths.add(path);
                }
            }
        }

        Product product = Product.builder()
                .productName(productName)
                .price(price)
                .salesLink(salesLink)
                .reviewLink(reviewLink)
                .imagePath(String.join(",", imagePaths)) // 임시로 문자열 저장
                .build();

        productRepository.save(product);
    }

    public ProductDto read(Integer idx) {
        Product product = productRepository.findById(idx)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));

        return toDto(product);
    }

    public ProductRes list() {
        List<ProductDto> products = productRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());

        return ProductRes.builder()
                .code(1000)
                .message("상품 불러오기에 성공하였습니다")
                .isSuccess(true)
                .success(true)
                .result(products)
                .build();
    }

    public void update(Integer idx,
                       String productName,
                       Integer price,
                       String salesLink,
                       String reviewLink,
                       MultipartFile image) throws IOException {

        Product product = productRepository.findById(idx)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));

        product.setProductName(productName);
        product.setPrice(price);
        product.setSalesLink(salesLink);
        product.setReviewLink(reviewLink);

        if (image != null && !image.isEmpty()) {
            product.setImagePath(gcsFileService.upload(image));
        }

        productRepository.save(product);
    }

    public void delete(Integer idx) {
        productRepository.deleteById(idx);
    }

    private ProductDto toDto(Product product) {
        return ProductDto.builder()
                .idx(product.getIdx())
                .productName(product.getProductName())
                .price(product.getPrice())
                .salesLink(product.getSalesLink())
                .reviewLink(product.getReviewLink())
                .imagePath(product.getImagePath())
                .build();
    }
}