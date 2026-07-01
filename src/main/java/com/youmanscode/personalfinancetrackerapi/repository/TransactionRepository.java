package com.youmanscode.personalfinancetrackerapi.repository;

import com.youmanscode.personalfinancetrackerapi.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
