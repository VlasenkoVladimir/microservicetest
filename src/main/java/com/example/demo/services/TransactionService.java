package com.example.demo.services;

import com.example.demo.domain.AccountLimits;
import com.example.demo.domain.CurrencyValue;
import com.example.demo.dto.TransactionDto;
import com.example.demo.enums.ExpenseCategory;
import com.example.demo.mappers.ObjectMapper;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.utils.CurrencyTableCache;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.demo.config.Constants.DEFAULT_LIMIT_CURRENCY;

/**
 * Сервис для Transaction
 */

@Slf4j
@Validated
@Service
@RequiredArgsConstructor
public class TransactionService {

    protected final TransactionRepository transactionRepository;
    protected final AccountLimitsService accountLimitsService;
    protected final CurrencyTableCache currencyTableCache;

    private final ObjectMapper objectMapper;

    public TransactionDto processTransaction(@Valid final TransactionDto transactionDto) {

        ExpenseCategory category = ExpenseCategory.valueOf(transactionDto.getExpenseCategory());
        AccountLimits accountLimits = accountLimitsService.
            findByAccountNumberAndCategory(transactionDto.getAccountFrom(), category);
        CurrencyValue currencyValue = currencyTableCache.getCurrentExchangeRate(transactionDto.getCurrencyShortname());
        BigDecimal sumEqualsDefaultCurrency = getSumEqualsDefaultCurrency(transactionDto, currencyValue);

        transactionDto.setLimitSum(accountLimits.getLimit());
        transactionDto.setLimitDatetime(accountLimits.getLimitDatetime());
        transactionDto.setCurrencyShortname(DEFAULT_LIMIT_CURRENCY);
        BigDecimal newLimitBalance = accountLimits.getLimitBalance().subtract(sumEqualsDefaultCurrency);

        if (newLimitBalance.compareTo(BigDecimal.ZERO) < 0) {
            transactionDto.setLimitExceeded(true);
        }

        return objectMapper.transactionToTransactionDto(transactionRepository.save(
            objectMapper.transactionDtoToTransaction(transactionDto)));
    }

    public List<TransactionDto> getLimitExceededTransactions(final Long account) {

        return transactionRepository.findLimitExceededTransactions(account).parallelStream()
            .map(objectMapper::transactionToTransactionDto)
            .collect(Collectors.toList());
    }

    private static BigDecimal getSumEqualsDefaultCurrency(TransactionDto transactionDto, CurrencyValue currencyValue) {

        BigDecimal sumEqualsDefaultCurrency;

        if (!transactionDto.getCurrencyShortname().equals(DEFAULT_LIMIT_CURRENCY)) {
            if (currencyValue.getClose() != null) {
                sumEqualsDefaultCurrency = transactionDto.getSum().multiply(currencyValue.getClose());
            } else {
                sumEqualsDefaultCurrency = transactionDto.getSum().multiply(currencyValue.getPreviousClose());
            }

        } else {
            sumEqualsDefaultCurrency = transactionDto.getSum();
        }

        return sumEqualsDefaultCurrency;
    }
}