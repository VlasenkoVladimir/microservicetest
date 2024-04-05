package com.example.demo.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public final class Violation {

	private final String property;
	private final String message;

	public Violation(String property, String message) {
		this.property = property;
		this.message = message;
	}
}