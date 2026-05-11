package com.example.demo.account.repository;

import com.example.demo.account.model.AccountBookItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountBookRepository extends JpaRepository<AccountBookItem, Integer> {

    List<AccountBookItem> findByBoardIdxOrderByUsedDateAscIdxAsc(Integer boardIdx);

    void deleteByBoardIdx(Integer boardIdx);
}