package com.example.demo.account.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountBookDto {

    private Integer idx;
    private Integer boardIdx;

    private String usedDate;
    private String dayOfWeek;

    private String content;
    private Integer amount;
    private String detail;
    private String card;
}