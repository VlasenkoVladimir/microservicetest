package com.example.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 * Сущность транзакции
 */

@Entity
@Table(name = "transactions")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Transaction extends GenericModel {

    @Column(name = "account_from", nullable = false)
    private Long accountFrom;

    @Column(name = "account_to", nullable = false)
    private Long accountTo;

    @Column(name = "currency_shortname", nullable = false)
    private String currencyShortname;

    @Column(name = "sum", nullable = false)
    private BigDecimal sum;

    @Column(name = "expanse_category", nullable = false)
    private String expenseCategory;

    @Column(name = "datetime", nullable = false)
    private Calendar datetime;

    @Column(name = "limit_exceeded", nullable = false)
    private Boolean limitExceeded;

    @Column(name = "limit_sum", nullable = false)
    @DecimalMin(value = "0.00", inclusive = false)
    @Digits(integer =16, fraction = 2)
    private BigDecimal limitSum;

    @Column(name = "limit_datetime", nullable = false)
    private Calendar limitDatetime;

    @Column(name = "limit_currency_shortname")
    @NotBlank
    private String limitCurrencyShortname;
}