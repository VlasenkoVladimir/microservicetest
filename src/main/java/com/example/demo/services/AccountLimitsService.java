package com.example.demo.services;

import com.example.demo.domain.AccountLimits;
import com.example.demo.dto.AccountLimitsDto;
import com.example.demo.enums.ExpenseCategory;
import com.example.demo.mappers.ObjectMapper;
import com.example.demo.repository.AccountLimitsRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 * Сервис для AccountLimits
 */

@Slf4j
@Validated
@Service
@RequiredArgsConstructor
public class AccountLimitsService {

    protected final AccountLimitsRepository accountLimitsRepository;

    private final ObjectMapper objectMapper;

    public AccountLimitsDto setNewLimit(@Valid final Long accountNumber,
                                        @Valid final ExpenseCategory category,
                                        @Valid final BigDecimal newLimit) {

        AccountLimits accountLimits = findByAccountNumberAndCategory(accountNumber, category);

        accountLimits.setLimitDatetime(Calendar.getInstance());
        accountLimits.setLimitBalance(accountLimits
            .getLimitBalance()
            .subtract(accountLimits.getLimit())
            .add(newLimit));
        accountLimits.setLimit(newLimit);
        return objectMapper.accountLimitsToAccountLimitsDto(accountLimitsRepository.save(accountLimits));

    }

    public AccountLimits findByAccountNumberAndCategory(final Long accountNumber, final ExpenseCategory category) {

        return switch (category) {
            case PRODUCT ->
                accountLimitsRepository.findAccountLimitsByAccountAndExpenseCategoryProduct(accountNumber).orElseThrow();
            case SERVICE ->
                accountLimitsRepository.findAccountLimitsByAccountAndExpenseCategoryService(accountNumber).orElseThrow();
        };
    }
}