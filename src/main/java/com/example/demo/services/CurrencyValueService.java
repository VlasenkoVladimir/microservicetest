package com.example.demo.services;

import com.example.demo.dto.CurrencyValueDto;
import com.example.demo.mappers.ObjectMapper;
import com.example.demo.repository.CurrencyValueRepository;
import com.example.demo.utils.ExchangeApiClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Calendar;

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

    private final ObjectMapper objectMapper;

    public CurrencyValueDto getTodayCurrencyValue(final String currency) {

        String request = currency + "/" + DEFAULT_LIMIT_CURRENCY + DEFAULT_EXCHANGE_PRODUCER_KEY;

        CurrencyValueDto result = exchangeApiClient.getExchangeRate(request);
        result.setDatetime(Calendar.getInstance());
        currencyValueRepository.save(objectMapper.currencyValueDtoToCurrencyValue(result));

        return result;
    }
}