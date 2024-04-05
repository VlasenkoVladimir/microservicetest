package com.example.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 * Сущность для хранения ограничений расходов по категориям для аккаунта
 */

@Entity
@Table(name = "account_limits")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccountLimits extends GenericModel {

    @Column(name = "account", nullable = false)
    private Long account;

    @Column(name = "products_limit_datetime", nullable = false)
    private Calendar productsLimitDatetime;

    @Column(name = "products_limit", nullable = false)
    private BigDecimal productsLimit;

    @Column(name = "products_limit_balance", nullable = false)
    private BigDecimal productsLimitBalance;

    @Column(name = "services_limit_datetime", nullable = false)
    private Calendar servicesLimitDatetime;

    @Column(name = "services_limit", nullable = false)
    private BigDecimal servicesLimit;

    @Column(name = "services_limit_balance", nullable = false)
    private BigDecimal servicesLimitBalance;
}