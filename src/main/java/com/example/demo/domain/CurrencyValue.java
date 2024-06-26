package com.example.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

/**
 * Курс валюты
 */

@Entity
@Table(name = "currencies")
@NoArgsConstructor
@Getter
@Setter
public class CurrencyValue extends GenericModel {

    @Column(name = "symbol", nullable = false)
    private String symbol;

    @Column(name = "close")
    private BigDecimal close;

    @Column(name = "previous_close", nullable = false)
    private BigDecimal previousClose;

    @Column(name = "datetime", nullable = false)
    private ZonedDateTime datetime;
}