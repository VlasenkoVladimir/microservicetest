package com.example.demo.dto;


import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 * DTO-класс для транзакций
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TransactionDto {

    @Range(min =1L, max = 9_999_999_999L)
    private Long accountFrom;

    @Range(min =1L, max = 9_999_999_999L)
    private Long accountTo;

    @NotBlank(message = "Please provide a Currency Shortname")
    private String currencyShortname;

    @NotBlank(message = "Please provide a sum")
    private BigDecimal sum;

    @NotBlank(message = "Please provide an Expense Category")
    private String expenseCategory;

    @NotBlank(message = "Required field")
    private Calendar datetime;

    private Boolean limitExceeded;

    private BigDecimal limitSum;

    private Calendar limitDatetime;

    private String limitCurrencyShortname;
}