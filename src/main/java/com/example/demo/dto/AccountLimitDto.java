package com.example.demo.dto;

import com.example.demo.enums.ExpenseCategory;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 * DTO-класс для ограничений по категориям расходов аккаунта
 */

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AccountLimitDto {

    @NotNull
    @Range(min = 1L, max = 9_999_999_999L)
    private Long account;

    @NotEmpty(message = "Required limitExpense")
    private ExpenseCategory expenseCategory;

    @NotEmpty(message = "Required LimitDatetime")
    private Calendar limitDatetime;

    @DecimalMin(value = "0.00", inclusive = false)
    @Digits(integer = 9, fraction = 2)
    private BigDecimal limit;

    @DecimalMin(value = "0.00", inclusive = false)
    @Digits(integer = 9, fraction = 2)
    private BigDecimal limitBalance;

    @Min(value = 1, message = "Required number of month, min = 1")
    @Max(value = 12, message = "Required number of month, max = 12")
    private Integer monthOfBalance;
}