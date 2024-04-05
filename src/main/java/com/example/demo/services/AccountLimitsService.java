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

        AccountLimits accountLimits = findByAccountNumber(accountNumber);

        switch (category) {
            case PRODUCT:
                accountLimits.setProductsLimitDatetime(Calendar.getInstance());
                accountLimits.setProductsLimitBalance(accountLimits
                    .getProductsLimitBalance()
                    .subtract(accountLimits.getProductsLimit())
                    .add(newLimit));
                accountLimits.setProductsLimit(newLimit);
                return objectMapper.accountLimitsToAccountLimitsDto(accountLimitsRepository.save(accountLimits));
            case SERVICE:
                accountLimits.setServicesLimitDatetime(Calendar.getInstance());
                accountLimits.setServicesLimitBalance(accountLimits
                    .getServicesLimitBalance()
                    .subtract(accountLimits.getServicesLimit())
                    .add(newLimit));
                accountLimits.setServicesLimit(newLimit);
                return objectMapper.accountLimitsToAccountLimitsDto(accountLimitsRepository.save(accountLimits));
            default:
                throw new IllegalStateException("Unexpected value: " + category);
        }
    }

    public AccountLimits findByAccountNumber(final Long accountNumber) {

        return accountLimitsRepository.findAccountLimitsByAccount(accountNumber).orElseThrow();
    }
}