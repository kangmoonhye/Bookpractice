package com.example.demo.product.controller;

import com.example.demo.product.model.dto.ProductDto;
import com.example.demo.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity create(ProductDto productDto){
        productService.create(productDto);
        return ResponseEntity.ok().body("상품을 만들었습니다");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/read")
    public ResponseEntity read(){

        productService.read();

        return ResponseEntity.ok().body("");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity list(Integer idx){

        productService.list(idx);


        return ResponseEntity.ok().body("");
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/update")
    public ResponseEntity update(){

        productService.update();

        return ResponseEntity.ok().body("");
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/delete")
    public ResponseEntity delete(Integer idx){

        productService.delte(idx);

        return ResponseEntity.ok().body("상품이 삭제 되었습니다");
    }






}
