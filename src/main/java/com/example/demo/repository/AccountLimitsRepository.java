package com.example.demo.repository;

import com.example.demo.domain.AccountLimits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Репозиторий для ограничений по аккаунту
 */

@Repository
public interface AccountLimitsRepository extends JpaRepository<AccountLimits, Long> {

    Optional<AccountLimits> findAccountLimitsByAccountAndExpenseCategoryProduct(Long account);

    Optional<AccountLimits> findAccountLimitsByAccountAndExpenseCategoryService(Long account);
}