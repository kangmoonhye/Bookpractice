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
        return ResponseEntity.ok().body("책 생성");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/read")
    public ResponseEntity read(){

        productService.read();

        return ResponseEntity.ok().body("");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity list(){

        productService.list();


        return ResponseEntity.ok().body("");
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/update")
    public ResponseEntity update(){

        productService.update();

        return ResponseEntity.ok().body("");
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/delete")
    public ResponseEntity delete(){

        productService.deelte();

        return ResponseEntity.ok().body("");
    }






}
