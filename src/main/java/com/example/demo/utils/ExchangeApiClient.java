package com.example.demo.utils;

import com.example.demo.dto.CurrencyValueDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.example.demo.config.Constants.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient(
    name = DEFAULT_EXCHANGE_PRODUCER,
    url = DEFAULT_EXCHANGE_PRODUCER_URL,
    path = DEFAULT_EXCHANGE_PRODUCER_PATH)
public interface ExchangeApiClient {

    @RequestMapping(method = GET, value = "/", consumes = APPLICATION_JSON_VALUE)
    CurrencyValueDto getExchangeRate(@RequestParam("symbol") final String symbol);
}