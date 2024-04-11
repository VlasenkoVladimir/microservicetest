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

import static com.example.demo.config.Constants.DEFAULT_ACCOUNT_LIMIT_VALUE;

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

    public AccountLimitDto setNewLimit(@Valid final AccountLimitDto accountLimitDto) {

        Long accountNumber = accountLimitDto.getAccount();
        ExpenseCategory category = accountLimitDto.getExpenseCategory();
        BigDecimal newLimit = accountLimitDto.getLimit();
        AccountLimit accountLimit = findByAccountNumberAndCategory(accountNumber, category);
        Calendar calendar = Calendar.getInstance();

        accountLimit.setLimitDatetime(calendar);
        accountLimit.setMonthOfBalance(calendar.get(Calendar.MONTH));

        if (newLimit.equals(BigDecimal.ZERO)) {
            newLimit = DEFAULT_ACCOUNT_LIMIT_VALUE;
        }

        accountLimit.setLimitBalance(accountLimit.getLimitBalance().subtract(accountLimit.getLimit()).add(newLimit));
        accountLimit.setLimit(newLimit);

        return objectMapper.accountLimitToAccountLimitDto(accountLimitsRepository.save(accountLimit));

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