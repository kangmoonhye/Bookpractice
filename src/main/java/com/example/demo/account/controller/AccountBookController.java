package com.example.demo.account.controller;

import com.example.demo.account.model.dto.AccountBookDto;
import com.example.demo.account.model.dto.AccountBookRes;
import com.example.demo.account.service.AccountBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/boards/{boardIdx}/account-books")
@RequiredArgsConstructor
public class AccountBookController {

    private final AccountBookService accountBookService;

    @GetMapping
    public ResponseEntity<AccountBookRes> list(
            @PathVariable Integer boardIdx
    ) {
        return ResponseEntity.ok(accountBookService.list(boardIdx));
    }

    @PostMapping
    public ResponseEntity<AccountBookDto> create(
            @PathVariable Integer boardIdx,
            @RequestParam String usedDate,
            @RequestParam String content,
            @RequestParam Integer amount,
            @RequestParam(required = false) String detail,
            @RequestParam String card
    ) {
        return ResponseEntity.ok(
                accountBookService.create(boardIdx, usedDate, content, amount, detail, card)
        );
    }

    @PatchMapping("/{idx}")
    public ResponseEntity<AccountBookDto> update(
            @PathVariable Integer idx,
            @RequestParam String usedDate,
            @RequestParam String content,
            @RequestParam Integer amount,
            @RequestParam(required = false) String detail,
            @RequestParam String card
    ) {
        return ResponseEntity.ok(
                accountBookService.update(idx, usedDate, content, amount, detail, card)
        );
    }

    @DeleteMapping("/{idx}")
    public ResponseEntity<String> delete(
            @PathVariable Integer idx
    ) {
        accountBookService.delete(idx);
        return ResponseEntity.ok("삭제 완료");
    }
}