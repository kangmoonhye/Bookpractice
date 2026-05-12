package com.example.demo.board.model;

import com.example.demo.product.model.Product;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @Column(nullable = false, length = 100)
    private String name;

    @OneToMany(mappedBy = "board")
    private List<Product> products;

    @Column(length = 255)
    private String password;

    @Column(nullable = false, length = 30)
    private String type; // product, account
}