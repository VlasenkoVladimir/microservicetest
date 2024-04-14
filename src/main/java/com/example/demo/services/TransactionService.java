package com.example.demo.services;

import com.example.demo.domain.AccountLimit;
import com.example.demo.domain.CurrencyValue;
import com.example.demo.dto.TransactionDto;
import com.example.demo.enums.ExpenseCategory;
import com.example.demo.mappers.ObjectMapper;
import com.example.demo.repository.TransactionRepository;
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
    protected final AccountLimitService accountLimitService;
    protected final CurrencyCacheService currencyCacheService;

    private final ObjectMapper objectMapper;

    public TransactionDto processTransaction(@Valid final TransactionDto transactionDto) {

        log.error("Error in #processTransaction with TransactionDto: {} at service layer", transactionDto);

        ExpenseCategory category = ExpenseCategory.valueOf(transactionDto.getExpenseCategory());
        AccountLimit accountLimit = accountLimitService.
            findByAccountNumberAndCategory(transactionDto.getAccountFrom(), category);
        BigDecimal sumEqualsDefaultCurrency = getSumEqualsDefaultCurrency(transactionDto);

        transactionDto.setLimitSum(accountLimit.getLimit());
        transactionDto.setLimitDatetime(accountLimit.getLimitDatetime());
        transactionDto.setLimitCurrencyShortname(DEFAULT_LIMIT_CURRENCY);
        BigDecimal newLimitBalance = accountLimitService.getLimitForActualMonth(accountLimit).
            subtract(sumEqualsDefaultCurrency);

        if (newLimitBalance.compareTo(BigDecimal.ZERO) < 0) {
            transactionDto.setLimitExceeded(true);
        }

        return objectMapper.transactionToTransactionDto(transactionRepository.save(
            objectMapper.transactionDtoToTransaction(transactionDto)));
    }

    public List<TransactionDto> getLimitExceededTransactions(final Long account) {

        log.error("Error in #getLimitExceededTransactions for account number: {} at service layer", account);

        return transactionRepository.findLimitExceededTransactions(account).parallelStream()
            .map(objectMapper::transactionToTransactionDto)
            .collect(Collectors.toList());
    }

    private BigDecimal getSumEqualsDefaultCurrency(final TransactionDto transactionDto) {

        log.error("Error in #getSumEqualsDefaultCurrency at service layer with TransactionDto: {}", transactionDto);

        CurrencyValue currencyValue = currencyCacheService.getCurrentExchangeRate(transactionDto.getCurrencyShortname());
        BigDecimal sumEqualsDefaultCurrency;

        if (!transactionDto.getCurrencyShortname().trim().equalsIgnoreCase(DEFAULT_LIMIT_CURRENCY)) {
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