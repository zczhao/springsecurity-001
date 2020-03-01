//package com.itheima.controller.advice;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerExceptionResolver;
//import org.springframework.web.servlet.ModelAndView;
//
//@Component
//public class HandlerControllerException implements HandlerExceptionResolver{
//
//	/**
//	 * @param request
//	 * @param response
//	 * @param o 出现异常的对象
//	 * @param e 出现的异常信息
//	 * @return ModelAndView
//	 */
//	@Override
//	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o,
//			Exception e) {
//		ModelAndView modelAndView = new ModelAndView();
//		// 将异常信息放入request域中，基本不用
//		modelAndView.addObject("errorMsg", e.getMessage());
//		// 指定不同异常跳转的页面
//		if (e instanceof AccessDeniedException) {
//			// forward/redirect都可以
//			modelAndView.setViewName("redirect:/403.jsp");
//		} else {
//			modelAndView.setViewName("redirect:/500.jsp");
//		}
//		return modelAndView;
//	}
//	
//}
