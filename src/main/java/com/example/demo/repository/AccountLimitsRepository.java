package com.example.demo.repository;

import com.example.demo.domain.AccountLimit;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Репозиторий для ограничений по аккаунту
 */

@Repository
public interface AccountLimitsRepository extends JpaRepository<AccountLimit, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<AccountLimit> findAccountLimitsByAccountAndExpenseCategoryProduct(Long account);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<AccountLimit> findAccountLimitsByAccountAndExpenseCategoryService(Long account);
}