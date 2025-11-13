package com.kt.domain.product;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class ProductTest {
	// 객체 생성이 잘되는가?
	// 제목을 작성하는 2가지 방법
	// 1. @DisplayName
	// 2. 메소드명 자체를 한글로 작성 (공백은 언더바로 대체) (v)

	@Test
	void 객체_생성_성공() {
		var product = new Product(
			"테스트 상품명",
			100_000L,
			10L
		);

		// 객체가 잘 생성되었나
		// product의 이름 필드의 값이 "테스트 상품명" 인가 ?
		// jupiter.core -> assertThat
		// jupiter.api -> assertEquals
		assertThat(product.getName()).isEqualTo("테스트 상품명");
		assertThat(product.getPrice()).isEqualTo(100_000L);
		assertThat(product.getStock()).isEqualTo(10L);
	}

	// 하고자 하는걸 적고 뒤에 이유를 적음

	@ParameterizedTest
	@NullAndEmptySource
	void 상품_생성_실패__상품명_null_이거나_공백(String name) {
		assertThrowsExactly(IllegalArgumentException.class, ()-> new Product(
				name,
				100_1000L,
				10L
			));
	}

	@Test
	void 상품_생성_실패__가격이_음수() {
		assertThrowsExactly(IllegalArgumentException.class, ()-> new Product(
			"테스트 상품명",
			-1L,
			10L
		));
	}

	@Test
	void 상품_생성_실패__가격이_null() {
		assertThrowsExactly(IllegalArgumentException.class, ()-> new Product(
			"테스트 상품명",
			null,
			10L
		));
	}

}