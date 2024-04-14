package com.example.demo.services;

import com.example.demo.domain.CurrencyValue;
import com.example.demo.repository.CurrencyValueRepository;
import com.example.demo.utils.ExchangeApiClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

import static com.example.demo.config.Constants.DEFAULT_EXCHANGE_PRODUCER_KEY;
import static com.example.demo.config.Constants.DEFAULT_LIMIT_CURRENCY;

/**
 * Сервис для CurrencyValue
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class CurrencyValueService {

    protected final ExchangeApiClient exchangeApiClient;

    protected final CurrencyValueRepository currencyValueRepository;

    public CurrencyValue getTodayCurrencyValue(final String currency) {

        log.info("#getTodayCurrencyValue request external exchange rate of currency: {}", currency);

        String request = currency + "/" + DEFAULT_LIMIT_CURRENCY + DEFAULT_EXCHANGE_PRODUCER_KEY;

        CurrencyValue result = exchangeApiClient.getExchangeRate(request);
        result.setDatetime(ZonedDateTime.now());

        return currencyValueRepository.save(result);
    }
}