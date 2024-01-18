package com.example.demo.product.repository;

import com.example.demo.product.model.Product;
import com.example.demo.product.model.dto.ProductDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    public Optional<Product> findById(Integer idx);

    public Optional<ProductDto> findByName(String name);

    List<ProductDto> list = new ArrayList<>();
}
