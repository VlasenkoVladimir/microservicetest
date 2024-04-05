package com.example.demo.repository;

import com.example.demo.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий для транзакция
 */

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findLimitExceededTransactions(Long account);
}