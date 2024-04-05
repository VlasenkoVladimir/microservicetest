package com.example.demo.controllers.rest.impl;


import com.example.demo.controllers.rest.AccountLimitsController;
import com.example.demo.dto.AccountLimitsDto;
import com.example.demo.enums.ExpenseCategory;
import com.example.demo.services.AccountLimitsService;
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
public class AccountLimitsControllerImpl implements AccountLimitsController {

    private final AccountLimitsService accountLimitsService;

    @Override
    public ResponseEntity<AccountLimitsDto> setNewLimit(Long accountNumber,
                                                        ExpenseCategory category,
                                                        BigDecimal newLimit) {

        return new ResponseEntity<>(accountLimitsService.setNewLimit(accountNumber, category, newLimit), CREATED);
    }
}