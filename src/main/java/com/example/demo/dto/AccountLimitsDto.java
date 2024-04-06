package com.example.demo.dto;

import com.example.demo.enums.ExpenseCategory;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;
import java.util.Calendar;

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

    @NotEmpty(message = "Required limitExpense")
    private ExpenseCategory ExpenseCategory;

    @NotEmpty(message = "Required LimitDatetime")
    private Calendar LimitDatetime;

    @DecimalMin(value = "0.00", inclusive = false)
    @Digits(integer = 9, fraction = 2)
    private BigDecimal limit;

    @DecimalMin(value = "0.00", inclusive = false)
    @Digits(integer = 9, fraction = 2)
    private BigDecimal limitBalance;
}