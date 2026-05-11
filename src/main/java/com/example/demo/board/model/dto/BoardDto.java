package com.example.demo.board.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDto {
    private Integer idx;
    private String name;
    private String password;
}