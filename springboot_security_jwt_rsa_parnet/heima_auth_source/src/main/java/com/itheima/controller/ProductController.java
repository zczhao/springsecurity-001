package com.itheima.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Secured("ROLE_PRODUCT")
	@GetMapping("/findAll")
	public String findAll() {
		String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return username + "产品列表查询成功";
	}
}
