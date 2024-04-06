package com.example.demo.controllers.rest.impl;

import com.example.demo.controllers.rest.TransactionController;
import com.example.demo.dto.TransactionDto;
import com.example.demo.services.TransactionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

/**
 * Реализация контроллера лимитов
 */

@RestController
@AllArgsConstructor
@Slf4j
public class TransactionControllerImpl implements TransactionController {

    private final TransactionService transactionService;

    @Override
    public ResponseEntity<TransactionDto> createTransaction(TransactionDto transactionDto) {

        return new ResponseEntity<>(transactionService.processTransaction(transactionDto), CREATED);
    }

    @Override
    public ResponseEntity<List<TransactionDto>> getLimitExceededTransactions(Long account) {
        return new ResponseEntity<>(transactionService.getLimitExceededTransactions(account), OK);
    }
}