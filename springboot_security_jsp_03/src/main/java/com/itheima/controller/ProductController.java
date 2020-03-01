package com.itheima.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Secured("ROLE_PRODUCT")
	@GetMapping("/findAll")
	public String findAll() {
		return "product-list";
	}
}
