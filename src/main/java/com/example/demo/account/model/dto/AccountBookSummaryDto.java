package com.example.demo.account.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountBookSummaryDto {

    private Integer totalAmount;
    private Integer hyundaiAmount;
    private Integer samsungAmount;
}