package com.kt.domain.payment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentType {
	CARD("카드"),
	CASH("현금"),
	PAY("간편결제");
	private final String description;
}
