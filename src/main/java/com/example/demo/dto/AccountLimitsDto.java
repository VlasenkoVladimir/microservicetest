package com.example.demo.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;
import java.util.Calendar;

import static com.example.demo.config.Constants.DEFAULT_PRODUCTS_LIMIT_VALUE;
import static com.example.demo.config.Constants.DEFAULT_SERVICES_LIMIT_VALUE;

/**
 * DTO-класс для ограничений по категориям расходов аккаунта
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountLimitsDto {

    @NotNull
    @Range(min = 1L, max = 9_999_999_999L)
    private Long account;

    @NotEmpty(message = "Required field")
    private Calendar limitDatetime;

    @DecimalMin(value = "0.00", inclusive = false)
    @Digits(integer = 9, fraction = 2)
    private BigDecimal productsLimit = DEFAULT_PRODUCTS_LIMIT_VALUE;

    @DecimalMin(value = "0.00", inclusive = false)
    @Digits(integer = 9, fraction = 2)
    private BigDecimal productsLimitBalance;

    @DecimalMin(value = "0.00", inclusive = false)
    @Digits(integer = 9, fraction = 2)
    private BigDecimal servicesLimit = DEFAULT_SERVICES_LIMIT_VALUE;

    @DecimalMin(value = "0.00", inclusive = false)
    @Digits(integer = 9, fraction = 2)
    private BigDecimal servicesLimitBalance;
}