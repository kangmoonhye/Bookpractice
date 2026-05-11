package com.example.demo.account.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountBookRes {

    private List<AccountBookDto> items;
    private AccountBookSummaryDto summary;
}