package com.example.demo.controllers.rest.impl;

import com.example.demo.controllers.rest.TransactionController;
import com.example.demo.dto.TransactionDto;
import com.example.demo.services.TransactionService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@Slf4j
public class TransactionControllerImpl implements TransactionController {

    private final TransactionService transactionService;

    private HttpSession httpSession;

    @Override
    public ResponseEntity<TransactionDto> processTransaction(TransactionDto transactionDto) {

        log.info("Received new transaction: {} with session id: {}", transactionDto, httpSession.getId());

        return new ResponseEntity<>(transactionService.processTransaction(transactionDto), CREATED);
    }

    @Override
    public ResponseEntity<List<TransactionDto>> getLimitExceededTransactions(Long account) {

        log.info("Requested Exceeded Transactions for account {} with session id: {}", account, httpSession.getId());

        return new ResponseEntity<>(transactionService.getLimitExceededTransactions(account), OK);
    }
}