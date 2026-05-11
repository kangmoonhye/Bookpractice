package com.example.demo.product.model;

import com.example.demo.board.model.Board;
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

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;
}
