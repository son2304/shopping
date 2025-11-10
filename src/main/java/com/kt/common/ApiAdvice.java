package com.kt.common;

import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.swagger.v3.oas.annotations.Hidden;

@Hidden
@RestControllerAdvice
public class ApiAdvice {
	// 어떤 예외를 처리할 것인지 정의
	// MethodArgumentNOtValidException 이 exception을 처리하도록
	// @ExceptionHandler(MethodArgumentNotValidException.class)
	// 500에러를 하나로 처리할 때
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse.ErrorData> internalServerError(Exception e) {
		e.printStackTrace();	// <- 어떤 에러가 뜬건지 알아볼 때
		// 서버 에러입니다
		return ErrorResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러입니다.백엔드 팀에 문의하세요.");
	}

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ErrorResponse.ErrorData> customException(CustomException e) {
		return ErrorResponse.error(e.getErrorCode().getStatus(), e.getErrorCode().getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse.ErrorData> methodArgumentNotValidException(MethodArgumentNotValidException e) {
		e.printStackTrace();
		var details = Arrays.toString(e.getDetailMessageArguments());
		var message = details.split(",",2)[1].replace("]","").trim();

		return ErrorResponse.error(HttpStatus.BAD_REQUEST, message);
	}
}
