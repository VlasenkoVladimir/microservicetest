package com.example.demo.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Класс для конфигурирования констант
 */

@Component
public class Constants {

    public static final BigDecimal DEFAULT_ACCOUNT_LIMIT_VALUE = new BigDecimal("${account.limit.defaultvalue}");

    public static final String DEFAULT_LIMIT_CURRENCY = "${account.limitcurrency.defaultvalue}";

    public static final String DEFAULT_EXCHANGE_PRODUCER = "twelvedata";

    public static final String DEFAULT_EXCHANGE_PRODUCER_URL = "https://api.twelvedata.com";

    public static final String DEFAULT_EXCHANGE_PRODUCER_PATH = "/quote";

    public static final String DEFAULT_EXCHANGE_PRODUCER_KEY = "&apikey=" + "${twelvedata.apikey}";
}