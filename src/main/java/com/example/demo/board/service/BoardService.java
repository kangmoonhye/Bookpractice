package com.example.demo.board.service;

import com.example.demo.account.repository.AccountBookRepository;
import com.example.demo.board.model.Board;
import com.example.demo.board.model.dto.BoardDto;
import com.example.demo.board.repository.BoardRepository;
import com.example.demo.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final AccountBookRepository accountBookRepository;
    private final ProductRepository productRepository;

    public BoardDto create(String name, String password) {
        Board board = Board.builder()
                .name(name)
                .password(password)
                .build();

        Board saved = boardRepository.save(board);

        return toDto(saved);
    }

    public boolean checkPassword(Integer idx, String password) {

        Board board = boardRepository.findById(idx)
                .orElseThrow(() -> new IllegalArgumentException("게시판을 찾을 수 없습니다."));

        return board.getPassword() != null
                && board.getPassword().equals(password);
    }

    public BoardDto updatePassword(Integer idx, String password) {
        Board board = boardRepository.findById(idx)
                .orElseThrow(() -> new IllegalArgumentException("게시판을 찾을 수 없습니다."));

        if (password == null || password.trim().isEmpty()) {
            board.setPassword(null); // 비밀번호 해제
        } else {
            board.setPassword(password); // 비밀번호 설정
        }

        return toDto(boardRepository.save(board));
    }

    public List<BoardDto> list() {
        return boardRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private BoardDto toDto(Board board) {
        return BoardDto.builder()
                .idx(board.getIdx())
                .name(board.getName())
                .password(board.getPassword())
                .build();
    }

    public void delete(Integer idx) {
        accountBookRepository.deleteByBoardIdx(idx);
        productRepository.deleteByBoardIdx(idx);
        boardRepository.deleteById(idx);
    }

    public BoardDto update(Integer idx, String name, String password) {
        Board board = boardRepository.findById(idx)
                .orElseThrow(() -> new IllegalArgumentException("게시판을 찾을 수 없습니다."));

        board.setName(name);

        if (password == null || password.trim().isEmpty()) {
            board.setPassword(null);
        } else {
            board.setPassword(password);
        }

        Board saved = boardRepository.save(board);

        return toDto(saved);
    }




}