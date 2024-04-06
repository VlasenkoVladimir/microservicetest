package com.example.demo.domain;

import jakarta.persistence.*;

/**
 * Абстрактная сущность со служебной информацией
 */

@MappedSuperclass
public abstract class GenericModel {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}