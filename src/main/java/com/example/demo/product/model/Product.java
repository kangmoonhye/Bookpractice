package com.example.demo.product.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer idx;
    private String productName;
    private Integer price;

    @Column(columnDefinition = "TEXT")
    private String salesLink;

    @Column(columnDefinition = "TEXT")
    private String reviewLink;

    private String imagePath;
}
