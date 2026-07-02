package com.youmanscode.personalfinancetrackerapi.repository;

import com.youmanscode.personalfinancetrackerapi.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> getAccountsById(Long id);

}
