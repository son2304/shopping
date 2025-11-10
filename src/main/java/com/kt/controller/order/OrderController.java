package com.kt.controller.order;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kt.common.ApiResult;
import com.kt.dto.order.OrderRequest;
import com.kt.service.OrderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
	private final OrderService orderService;

	// 주문 생성
	@PostMapping
	public ApiResult<Void> create(@RequestBody @Valid OrderRequest.Create orderRequest) {
		orderService.create(
			orderRequest.userId(),
			orderRequest.productId(),
			orderRequest.receiverName(),
			orderRequest.receiverAddress(),
			orderRequest.receiverMobile(),
			orderRequest.quantity()
			);
		return ApiResult.ok();
	}
}
