package com.example.demo.board.controller;

import com.example.demo.board.model.dto.BoardDto;
import com.example.demo.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/create")
    public ResponseEntity<BoardDto> create(
            @RequestParam String name,
            @RequestParam(required = false) String password
    ) {
        return ResponseEntity.ok(boardService.create(name, password));
    }

    @PostMapping("/{idx}/check-password")
    public ResponseEntity<Boolean> checkPassword(
            @PathVariable Integer idx,
            @RequestParam String password
    ) {
        return ResponseEntity.ok(boardService.checkPassword(idx, password));
    }

    @PatchMapping("/update-password/{idx}")
    public ResponseEntity<BoardDto> updatePassword(
            @PathVariable Integer idx,
            @RequestParam(required = false) String password
    ) {
        return ResponseEntity.ok(boardService.updatePassword(idx, password));
    }

    @GetMapping("/list")
    public ResponseEntity<List<BoardDto>> list() {
        return ResponseEntity.ok(boardService.list());
    }

    @DeleteMapping("/delete/{idx}")
    public ResponseEntity<String> delete(
            @PathVariable Integer idx
    ) {
        boardService.delete(idx);

        return ResponseEntity.ok("게시판 삭제 완료");
    }

    @PatchMapping("/update/{idx}")
    public ResponseEntity<BoardDto> update(
            @PathVariable Integer idx,
            @RequestParam String name,
            @RequestParam(required = false) String password
    ) {
        return ResponseEntity.ok(
                boardService.update(idx, name, password)
        );
    }
}