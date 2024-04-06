package com.example.demo.services;

import com.example.demo.domain.AccountLimit;
import com.example.demo.dto.AccountLimitDto;
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
public class AccountLimitService {

    protected final AccountLimitsRepository accountLimitsRepository;

    private final ObjectMapper objectMapper;

    public AccountLimitDto setNewLimit(@Valid final Long accountNumber,
                                       @Valid final ExpenseCategory category,
                                       @Valid final BigDecimal newLimit) {

        AccountLimit accountLimit = findByAccountNumberAndCategory(accountNumber, category);

        accountLimit.setLimitDatetime(Calendar.getInstance());
        accountLimit.setLimitBalance(accountLimit
            .getLimitBalance()
            .subtract(accountLimit.getLimit())
            .add(newLimit));
        accountLimit.setLimit(newLimit);
        return objectMapper.accountLimitsToAccountLimitsDto(accountLimitsRepository.save(accountLimit));

    }

    public AccountLimit findByAccountNumberAndCategory(final Long accountNumber, final ExpenseCategory category) {

        return switch (category) {
            case PRODUCT ->
                accountLimitsRepository.findAccountLimitsByAccountAndExpenseCategoryProduct(accountNumber).orElseThrow();
            case SERVICE ->
                accountLimitsRepository.findAccountLimitsByAccountAndExpenseCategoryService(accountNumber).orElseThrow();
        };
    }
}