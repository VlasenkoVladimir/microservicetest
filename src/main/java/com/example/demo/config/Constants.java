package com.example.demo.config;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Класс для конфигурирования констант
 */

@Component
public class Constants {

    public static final BigDecimal DEFAULT_PRODUCTS_LIMIT_VALUE = BigDecimal.valueOf(1000L);

    public static final BigDecimal DEFAULT_SERVICES_LIMIT_VALUE = BigDecimal.valueOf(1000L);

    public static final String DEFAULT_LIMIT_CURRENCY = "USD";

    public static final String DEFAULT_EXCHANGE_PRODUCER = "twelvedata";

    public static final String DEFAULT_EXCHANGE_PRODUCER_URL = "https://api.twelvedata.com";

    public static final String DEFAULT_EXCHANGE_PRODUCER_PATH = "/quote";

    public static final String DEFAULT_EXCHANGE_PRODUCER_KEY = "&apikey=c158428409ae436fa03c4091f95e65c1";
}