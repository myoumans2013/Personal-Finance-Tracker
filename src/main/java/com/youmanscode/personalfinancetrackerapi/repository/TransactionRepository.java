package com.youmanscode.personalfinancetrackerapi.repository;

import com.youmanscode.personalfinancetrackerapi.entity.Transaction;
import com.youmanscode.personalfinancetrackerapi.enums.Category;
import com.youmanscode.personalfinancetrackerapi.enums.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Boolean existsByAccount_Id(Long id);

    List<Transaction> findByAccount_Id(Long accountId);

    List<Transaction> findByCategoryOrderByTransactionDateDesc(Category category);

    List<Transaction> findByTransactionTypeOrderByTransactionDateDesc(TransactionType transactionType);

    Transaction findTransactionById(Long id);

    List<Transaction> findAllByOrderByTransactionDateDesc();

    List<Transaction> findByTransactionDateBetweenOrderByTransactionDateDesc(LocalDate start, LocalDate end);

}
