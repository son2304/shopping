package com.kt.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kt.domain.order.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	// 1. 네이티브 쿼리
	// 2. JPQL로 작성
	// 3. 쿼리 메소드로 작성
	// 4. 조회할때는 동적쿼리를 작성하게 해줄 수 있는 querydsl 사용하자
}
