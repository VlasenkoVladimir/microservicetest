package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Класс для конфигурирования констант
 */

@Component
public class Constants {

    @Value("${account.limit.defaultvalue}")
    public static final BigDecimal DEFAULT_ACCOUNT_LIMIT_VALUE = BigDecimal.valueOf(1000L);

    @Value("${account.limitcurrency.defaultvalue}")
    public static final String DEFAULT_LIMIT_CURRENCY = "USD";

    public static final String DEFAULT_EXCHANGE_PRODUCER = "twelvedata";

    public static final String DEFAULT_EXCHANGE_PRODUCER_URL = "https://api.twelvedata.com";

    public static final String DEFAULT_EXCHANGE_PRODUCER_PATH = "/quote";

    public static final String DEFAULT_EXCHANGE_PRODUCER_KEY = "&apikey=" + "${twelvedata.apikey}";

}