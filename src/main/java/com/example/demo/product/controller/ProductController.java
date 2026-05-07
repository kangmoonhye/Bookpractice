package com.example.demo.product.controller;

import com.example.demo.product.model.dto.ProductDto;
import com.example.demo.product.model.dto.ProductRes;
import com.example.demo.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<String> create(
            @RequestParam String productName,
            @RequestParam Integer price,
            @RequestParam String salesLink,
            @RequestParam String reviewLink,
            @RequestParam(required = false) List<MultipartFile> images
    ) throws IOException {

        productService.create(productName, price, salesLink, reviewLink, images);
        return ResponseEntity.ok("상품 생성 완료");
    }

    @GetMapping("/read/{idx}")
    public ResponseEntity<ProductDto> read(@PathVariable Integer idx) {
        return ResponseEntity.ok(productService.read(idx));
    }

    @GetMapping("/list")
    public ResponseEntity<ProductRes> list() {
        return ResponseEntity.ok(productService.list());
    }

    @PatchMapping("/update/{idx}")
    public ResponseEntity<String> update(
            @PathVariable Integer idx,
            @RequestParam String productName,
            @RequestParam Integer price,
            @RequestParam String salesLink,
            @RequestParam String reviewLink,
            @RequestParam(required = false) MultipartFile image
    ) throws IOException {

        productService.update(idx, productName, price, salesLink, reviewLink, image);

        return ResponseEntity.ok("상품을 수정했습니다.");
    }

    @DeleteMapping("/delete/{idx}")
    public ResponseEntity<String> delete(@PathVariable Integer idx) {
        productService.delete(idx);
        return ResponseEntity.ok("상품을 삭제했습니다.");
    }
}