package com.itheima.controller.advice;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionAdvice {

	@ExceptionHandler(RuntimeException.class)
	public String exceptionAdvice(RuntimeException e) {
		if (e instanceof AccessDeniedException) {
			return "forward:/403.jsp";
		}
		return "forward:/500.jsp";
	}

}
