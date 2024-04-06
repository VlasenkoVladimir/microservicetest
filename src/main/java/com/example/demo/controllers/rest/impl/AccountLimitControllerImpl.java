package com.example.demo.controllers.rest.impl;


import com.example.demo.controllers.rest.AccountLimitController;
import com.example.demo.dto.AccountLimitDto;
import com.example.demo.enums.ExpenseCategory;
import com.example.demo.services.AccountLimitService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * Реализация контроллера лимитов
 */

@RestController
@AllArgsConstructor
@Slf4j
public class AccountLimitControllerImpl implements AccountLimitController {

    private final AccountLimitService accountLimitService;

    @Override
    public ResponseEntity<AccountLimitDto> setNewLimit(Long accountNumber,
                                                       ExpenseCategory category,
                                                       BigDecimal newLimit) {

        return new ResponseEntity<>(accountLimitService.setNewLimit(accountNumber, category, newLimit), CREATED);
    }
}