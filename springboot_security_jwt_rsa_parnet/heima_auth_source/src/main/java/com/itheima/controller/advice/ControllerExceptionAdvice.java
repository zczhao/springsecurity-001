package com.itheima.controller.advice;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionAdvice {

	@ExceptionHandler(RuntimeException.class)
	public Map<String, Object> exceptionAdvice(RuntimeException e) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		if (e instanceof AccessDeniedException) {
			resultMap.put("code", HttpServletResponse.SC_FORBIDDEN);
			resultMap.put("msg", "没有权限访问");
			return resultMap;
		}
		resultMap.put("code", 9999);
		resultMap.put("msg", e.getMessage());
		return resultMap;
	}
}
