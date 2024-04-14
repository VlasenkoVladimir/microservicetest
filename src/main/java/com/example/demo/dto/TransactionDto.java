package com.example.demo.dto;


import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

/**
 * DTO-класс для транзакций
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TransactionDto {

    @Range(min = 1L, max = 9_999_999_999L, message = "Please provide an account number in range 1L - 9_999_999_999L")
    private Long accountFrom;

    @Range(min = 1L, max = 9_999_999_999L, message = "Please provide an account number in range 1L - 9_999_999_999L")
    private Long accountTo;

    @Size(min = 3, max = 3, message = "Please provide a Currency Shortname")
    private String currencyShortname;

    @Digits(integer = 16, fraction = 2, message = "Please provide a sum of transaction with round to 2 digits")
    private BigDecimal sum;

    @NotBlank(message = "Please provide an Expense Category")
    private String expenseCategory;

    @NotBlank(message = "Required field in ZonedDateTime format")
    private ZonedDateTime datetime;

    private Boolean limitExceeded;

    private BigDecimal limitSum;

    private ZonedDateTime limitDatetime;

    private String limitCurrencyShortname;
}