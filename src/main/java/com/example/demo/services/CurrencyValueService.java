package com.example.demo.services;

import com.example.demo.dto.CurrencyValueDto;
import com.example.demo.utils.ExchangeApiClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Calendar;

import static com.example.demo.config.Constants.DEFAULT_EXCHANGE_PRODUCER_KEY;
import static com.example.demo.config.Constants.DEFAULT_LIMIT_CURRENCY;

/**
 * Сервис для CurrencyValue
 */

@Slf4j
@Validated
@Service
@RequiredArgsConstructor
public class CurrencyValueService {

    private final ExchangeApiClient exchangeApiClient;

    public CurrencyValueDto getTodayCurrencyValue(final String currency) {
        String request = currency + "/" + DEFAULT_LIMIT_CURRENCY + DEFAULT_EXCHANGE_PRODUCER_KEY;
        CurrencyValueDto result = exchangeApiClient.getExchangeRate(request);
        result.setDatetime(Calendar.getInstance());

        return result;
    }
}