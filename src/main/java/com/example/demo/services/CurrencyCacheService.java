package com.example.demo.services;

import com.example.demo.domain.CurrencyValue;
import com.example.demo.mappers.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Вспомогательный сервис для хранения текущих кэшей курсов валют
 */

@Slf4j
@Service
@Getter
@RequiredArgsConstructor
public class CurrencyCacheService {

    private static final ConcurrentHashMap<String, CurrencyValue> currencyCache = new ConcurrentHashMap<>();

    private final CurrencyValueService currencyValueService;
    private final ObjectMapper objectMapper;

    public CurrencyValue getCurrentExchangeRate(final String currency) {

        log.info("#getCurrentExchangeRate request of currency: {} at service layer", currency);

        CurrencyValue result;

        if (currencyCache.containsKey(currency) & currencyCache.get(currency).getDatetime().getDayOfMonth() == ZonedDateTime.now().getDayOfMonth()) {
            result = currencyCache.get(currency);
        } else {
            result = currencyValueService.getTodayCurrencyValue(currency);
            currencyCache.put(currency, result);
        }

        return result;
    }
}