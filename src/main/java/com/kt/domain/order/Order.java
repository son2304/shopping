package com.kt.domain.order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.kt.common.BaseEntity;
import com.kt.domain.orderproduct.OrderProduct;
import com.kt.domain.user.User;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
	@Embedded
	private Receiver receiver;
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	private LocalDateTime deliveredAt;

	// 연관관계
	// 주문 <-> 회원
	// N : 1 => 다대일
	// ManyToOne : JPA에서 표현하는 방식
	// FK => 많은 쪽에 생김 (자동으로)

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	// 하나의 오더는 여러개의 상품 가능
	// 하나의 사품은 여러개의 오더 가능

	@OneToMany(mappedBy = "order")
	private List<OrderProduct> orderProducts = new ArrayList<>();

	// 주문 생성
	// 주문 상태 변경
	// 주문 생성 완료 - 재고 차감
	// 배송받는 사람 정보 변경
	//
}
