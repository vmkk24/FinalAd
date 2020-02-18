package com.advertisment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.advertisment.constant.Constant;
import com.advertisment.dto.ErrorDto;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorDto> userNotFoundException(UserNotFoundException e) {
		ErrorDto errorDto = new ErrorDto();
		errorDto.setMessage(e.getMessage());
		errorDto.setStatusCode(Constant.USER_NOT_FOUND_CODE);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
	}

	@ExceptionHandler(SlotNotFoundException.class)
	public ResponseEntity<ErrorDto> slotNotFoundException(SlotNotFoundException e) {
		ErrorDto errorDto = new ErrorDto();
		errorDto.setMessage(e.getMessage());
		errorDto.setStatusCode(Constant.SLOT_NOT_FOUND_CODE);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
	}

	@ExceptionHandler(PlanNotFoundException.class)
	public ResponseEntity<ErrorDto> planNotFoundException(PlanNotFoundException e) {
		ErrorDto errorDto = new ErrorDto();
		errorDto.setMessage(e.getMessage());
		errorDto.setStatusCode(Constant.PLAN_NOT_FOUND_CODE);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
	}

}
