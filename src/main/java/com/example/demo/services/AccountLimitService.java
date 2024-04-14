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
import java.time.ZonedDateTime;

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

        log.info("#setNewLimit with AccountLimitDto: {} at service layer", accountLimitDto);

        BigDecimal newLimit = accountLimitDto.getLimit();
        AccountLimit accountLimit = findByAccountNumberAndCategory(accountLimitDto.getAccount(), accountLimitDto.getExpenseCategory());
        ZonedDateTime dateTime = ZonedDateTime.now();

        accountLimit.setLimitDatetime(dateTime);
        accountLimit.setMonthOfBalance(dateTime.getMonthValue());

        if (newLimit.equals(BigDecimal.ZERO)) {
            newLimit = DEFAULT_ACCOUNT_LIMIT_VALUE;
        } else {
            accountLimit.setLimit(newLimit);
        }

        accountLimit.setLimitBalance(getLimitForActualMonth(accountLimit).subtract(accountLimit.getLimit()).add(newLimit));

        return objectMapper.accountLimitToAccountLimitDto(accountLimitsRepository.save(accountLimit));

    }

    public AccountLimit findByAccountNumberAndCategory(final Long accountNumber, final ExpenseCategory category) {

        log.error("Error in #findByAccountNumberAndCategory with accountNumber: {} and ExpenseCategory: {} at service layer", accountNumber, category);

        return switch (category) {
            case PRODUCT ->
                accountLimitsRepository.findAccountLimitsByAccountAndExpenseCategoryProduct(accountNumber).orElseThrow();
            case SERVICE ->
                accountLimitsRepository.findAccountLimitsByAccountAndExpenseCategoryService(accountNumber).orElseThrow();
        };
    }

    public BigDecimal getLimitForActualMonth(AccountLimit accountLimit) {

        log.error("Error in #getLimitForActualMonth with AccountLimit: {} at service layer", accountLimit);

        if (ZonedDateTime.now().getMonthValue() != accountLimit.getMonthOfBalance()) {
            accountLimit.setLimitBalance(accountLimit.getLimit());
        }

        return accountLimit.getLimitBalance();
    }
}