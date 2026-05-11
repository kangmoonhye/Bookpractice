package com.example.demo.account.model;

import com.example.demo.board.model.Board;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountBookItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @Column(nullable = false)
    private LocalDate usedDate;

    @Column(nullable = false, length = 100)
    private String content;

    @Column(nullable = false)
    private Integer amount;

    @Column(length = 255)
    private String detail;

    @Column(nullable = false, length = 20)
    private String card;
}