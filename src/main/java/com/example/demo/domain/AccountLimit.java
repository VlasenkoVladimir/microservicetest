package com.example.demo.domain;

import com.example.demo.enums.ExpenseCategory;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

/**
 * Сущность для хранения ограничений расходов по категориям для аккаунта
 */

@Entity
@Table(name = "account_limits")
@NoArgsConstructor
@Getter
@Setter
public class AccountLimit extends GenericModel {

    @Column(name = "account", nullable = false)
    private Long account;

    @Column(name = "expense_category", nullable = false)
    private ExpenseCategory expenseCategory;

    @Column(name = "limit_datetime", nullable = false)
    private ZonedDateTime limitDatetime;

    @Column(name = "limit", nullable = false)
    private BigDecimal limit;

    @Column(name = "limit_balance", nullable = false)
    private BigDecimal limitBalance;

    @Column(name = "month_of_balance", nullable = false)
    private Integer monthOfBalance;
}