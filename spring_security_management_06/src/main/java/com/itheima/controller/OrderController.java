package com.itheima.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@PreAuthorize("hasAnyRole('ROLE_ORDER','ROLE_ADMIN')")
	@RequestMapping("/findAll")
	public String findAll() {
		return "order-list";
	}
	
}
