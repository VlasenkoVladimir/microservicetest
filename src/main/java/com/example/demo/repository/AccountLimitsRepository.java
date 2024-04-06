package com.example.demo.repository;

import com.example.demo.domain.AccountLimit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Репозиторий для ограничений по аккаунту
 */

@Repository
public interface AccountLimitsRepository extends JpaRepository<AccountLimit, Long> {

    Optional<AccountLimit> findAccountLimitsByAccountAndExpenseCategoryProduct(Long account);

    Optional<AccountLimit> findAccountLimitsByAccountAndExpenseCategoryService(Long account);
}