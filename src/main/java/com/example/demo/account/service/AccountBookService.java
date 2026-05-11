package com.example.demo.account.service;

import com.example.demo.account.model.AccountBookItem;
import com.example.demo.account.model.dto.AccountBookDto;
import com.example.demo.account.model.dto.AccountBookRes;
import com.example.demo.account.model.dto.AccountBookSummaryDto;
import com.example.demo.account.repository.AccountBookRepository;
import com.example.demo.board.model.Board;
import com.example.demo.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountBookService {

    private final AccountBookRepository accountBookRepository;
    private final BoardRepository boardRepository;

    public AccountBookRes list(Integer boardIdx) {
        List<AccountBookItem> items =
                accountBookRepository.findByBoardIdxOrderByUsedDateAscIdxAsc(boardIdx);

        List<AccountBookDto> result = items.stream()
                .map(this::toDto)
                .collect(Collectors.toList());

        int total = items.stream()
                .mapToInt(AccountBookItem::getAmount)
                .sum();

        int hyundai = items.stream()
                .filter(item -> "현대".equals(item.getCard()))
                .mapToInt(AccountBookItem::getAmount)
                .sum();

        int samsung = items.stream()
                .filter(item -> "삼성".equals(item.getCard()))
                .mapToInt(AccountBookItem::getAmount)
                .sum();

        AccountBookSummaryDto summary = AccountBookSummaryDto.builder()
                .totalAmount(total)
                .hyundaiAmount(hyundai)
                .samsungAmount(samsung)
                .build();

        return AccountBookRes.builder()
                .items(result)
                .summary(summary)
                .build();
    }

    public AccountBookDto create(
            Integer boardIdx,
            String usedDate,
            String content,
            Integer amount,
            String detail,
            String card
    ) {
        Board board = boardRepository.findById(boardIdx)
                .orElseThrow(() -> new IllegalArgumentException("게시판을 찾을 수 없습니다."));

        AccountBookItem item = AccountBookItem.builder()
                .board(board)
                .usedDate(LocalDate.parse(usedDate))
                .content(content)
                .amount(amount)
                .detail(detail)
                .card(card)
                .build();

        AccountBookItem saved = accountBookRepository.save(item);

        return toDto(saved);
    }

    public AccountBookDto update(
            Integer idx,
            String usedDate,
            String content,
            Integer amount,
            String detail,
            String card
    ) {
        AccountBookItem item = accountBookRepository.findById(idx)
                .orElseThrow(() -> new IllegalArgumentException("가계부 항목을 찾을 수 없습니다."));

        item.setUsedDate(LocalDate.parse(usedDate));
        item.setContent(content);
        item.setAmount(amount);
        item.setDetail(detail);
        item.setCard(card);

        return toDto(accountBookRepository.save(item));
    }

    public void delete(Integer idx) {
        accountBookRepository.deleteById(idx);
    }

    private AccountBookDto toDto(AccountBookItem item) {
        return AccountBookDto.builder()
                .idx(item.getIdx())
                .boardIdx(item.getBoard().getIdx())
                .usedDate(item.getUsedDate().toString())
                .dayOfWeek(toKoreanDayOfWeek(item.getUsedDate().getDayOfWeek()))
                .content(item.getContent())
                .amount(item.getAmount())
                .detail(item.getDetail())
                .card(item.getCard())
                .build();
    }

    private String toKoreanDayOfWeek(DayOfWeek dayOfWeek) {
        switch (dayOfWeek) {
            case MONDAY:
                return "월";
            case TUESDAY:
                return "화";
            case WEDNESDAY:
                return "수";
            case THURSDAY:
                return "목";
            case FRIDAY:
                return "금";
            case SATURDAY:
                return "토";
            case SUNDAY:
                return "일";
            default:
                return "";
        }
    }
}