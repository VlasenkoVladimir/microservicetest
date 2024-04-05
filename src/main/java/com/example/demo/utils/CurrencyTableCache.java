package com.example.demo.utils;

import com.example.demo.domain.CurrencyValue;
import com.example.demo.mappers.ObjectMapper;
import com.example.demo.services.CurrencyValueService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Вспомогательный сервис для хранения текущих кэшей курсов валют
 */

@Service
@Getter
@AllArgsConstructor
public class CurrencyTableCache {

    private static final ConcurrentHashMap<String, CurrencyValue> currencyCache = new ConcurrentHashMap<>();

    private final CurrencyValueService currencyValueService;
    private final ObjectMapper objectMapper;

    public CurrencyValue getCurrentExchangeRate(final String currency) {

        CurrencyValue currencyValue;

        if (currencyCache.containsKey(currency)) {
            currencyValue = currencyCache.get(currency);
        } else {
            currencyValue = objectMapper.currencyValueDtoToCurrencyValue(currencyValueService.getTodayCurrencyValue(currency));
            currencyCache.put(currency, currencyValue);
        }

        return currencyValue;
    }
}