//package com.example.demo.product.controller;
//
//import com.example.demo.product.model.dto.ProductDto;
//import com.example.demo.product.model.dto.ProductRes;
//import com.example.demo.product.service.ProductService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.List;
//
//@CrossOrigin(origins = "*")
//@RestController
//@RequestMapping("/product")
//@RequiredArgsConstructor
//public class ProductController {
//
//    private final ProductService productService;
//
//    @PostMapping("/create")
//    public ResponseEntity<String> create(
//            @RequestParam(value = "productName") String productName,
//            @RequestParam(value = "price") Integer price,
//            @RequestParam(value = "salesLink") String salesLink,
//            @RequestParam(value = "reviewLink") String reviewLink,
//            @RequestParam(value = "images", required = false) List<MultipartFile> images
//    ) throws IOException {
//
//        System.out.println("[Product create] productName = " + productName);
//        System.out.println("[Product create] images = " + images);
//        System.out.println("[Product create] images size = " + (images == null ? 0 : images.size()));
//
//        if (images != null) {
//            for (MultipartFile image : images) {
//                System.out.println("[Product create] file name = " + image.getOriginalFilename());
//                System.out.println("[Product create] file empty = " + image.isEmpty());
//                System.out.println("[Product create] file size = " + image.getSize());
//                System.out.println("[Product create] content type = " + image.getContentType());
//            }
//        }
//
//        productService.create(productName, price, salesLink, reviewLink, images);
//
//        return ResponseEntity.ok("상품 생성 완료");
//    }
//
//    @GetMapping("/read/{idx}")
//    public ResponseEntity<ProductDto> read(@PathVariable Integer idx) {
//        return ResponseEntity.ok(productService.read(idx));
//    }
//
//    @GetMapping("/list")
//    public ResponseEntity<ProductRes> list() {
//        return ResponseEntity.ok(productService.list());
//    }
//
//    @PatchMapping("/update/{idx}")
//    public ResponseEntity<String> update(
//            @PathVariable Integer idx,
//            @RequestParam(value = "productName") String productName,
//            @RequestParam(value = "price") Integer price,
//            @RequestParam(value = "salesLink") String salesLink,
//            @RequestParam(value = "reviewLink") String reviewLink,
//            @RequestParam(value = "image", required = false) MultipartFile image
//    ) throws IOException {
//
//        System.out.println("[Product update] idx = " + idx);
//        System.out.println("[Product update] image = " + image);
//
//        if (image != null) {
//            System.out.println("[Product update] file name = " + image.getOriginalFilename());
//            System.out.println("[Product update] file empty = " + image.isEmpty());
//            System.out.println("[Product update] file size = " + image.getSize());
//            System.out.println("[Product update] content type = " + image.getContentType());
//        }
//
//        productService.update(idx, productName, price, salesLink, reviewLink, image);
//
//        return ResponseEntity.ok("상품을 수정했습니다.");
//    }
//
//    @DeleteMapping("/delete/{idx}")
//    public ResponseEntity<String> delete(@PathVariable Integer idx) {
//        productService.delete(idx);
//        return ResponseEntity.ok("상품을 삭제했습니다.");
//    }
//}



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

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // 기존 상품 생성 API 유지
    @PostMapping("/create")
    public ResponseEntity<String> create(
            @RequestParam(value = "productName") String productName,
            @RequestParam(value = "price") Integer price,
            @RequestParam(value = "salesLink") String salesLink,
            @RequestParam(value = "reviewLink") String reviewLink,
            @RequestParam(value = "images", required = false) List<MultipartFile> images
    ) throws IOException {

        productService.create(productName, price, salesLink, reviewLink, images);

        return ResponseEntity.ok("상품 생성 완료");
    }

    // 게시판 안 상품 생성 API
    @PostMapping("/boards/{boardIdx}/create")
    public ResponseEntity<String> createByBoard(
            @PathVariable Integer boardIdx,
            @RequestParam(value = "productName") String productName,
            @RequestParam(value = "price") Integer price,
            @RequestParam(value = "salesLink") String salesLink,
            @RequestParam(value = "reviewLink") String reviewLink,
            @RequestParam(value = "images", required = false) List<MultipartFile> images
    ) throws IOException {

        productService.createByBoard(boardIdx, productName, price, salesLink, reviewLink, images);

        return ResponseEntity.ok("게시판 상품 생성 완료");
    }

    @GetMapping("/read/{idx}")
    public ResponseEntity<ProductDto> read(@PathVariable Integer idx) {
        return ResponseEntity.ok(productService.read(idx));
    }

    // 기존 전체 상품 목록 API 유지
    @GetMapping("/list")
    public ResponseEntity<ProductRes> list() {
        return ResponseEntity.ok(productService.list());
    }

    // 게시판 안 상품 목록 API
    @GetMapping("/boards/{boardIdx}/list")
    public ResponseEntity<ProductRes> listByBoard(
            @PathVariable Integer boardIdx
    ) {
        return ResponseEntity.ok(productService.listByBoard(boardIdx));
    }

    @PatchMapping("/update/{idx}")
    public ResponseEntity<String> update(
            @PathVariable Integer idx,
            @RequestParam(value = "productName") String productName,
            @RequestParam(value = "price") Integer price,
            @RequestParam(value = "salesLink") String salesLink,
            @RequestParam(value = "reviewLink") String reviewLink,
            @RequestParam(value = "image", required = false) MultipartFile image
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