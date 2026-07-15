package com.youmanscode.personalfinancetrackerapi.repository;

import com.youmanscode.personalfinancetrackerapi.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
