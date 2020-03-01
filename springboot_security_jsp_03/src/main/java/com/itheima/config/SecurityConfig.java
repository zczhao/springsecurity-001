package com.itheima.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.itheima.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserService userService;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// 认证用户来源【内存或数据库】
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
	}

	// 配置SpringSecurity相关信息
	@Override
	public void configure(HttpSecurity http) throws Exception {
		// 释放静态资源、指定资源拦截规则、指定自定义认证页面、指定退出认证配置、csrf配置
		http.authorizeRequests()
		    // 释放静态资源
			.antMatchers("/login.jsp","/failer.jsp","/css/**","/img/**","/plugins/**").permitAll()
			// 指定资源拦截规则
			.antMatchers("/**").hasAnyRole("USER","ADMIN")
			.anyRequest()
			.authenticated()
			// 指定自定义认证页面
			.and()
			.formLogin().loginPage("/login.jsp")
			.loginProcessingUrl("/login")
			.defaultSuccessUrl("/index.jsp")
			.successForwardUrl("/index.jsp")
			.failureForwardUrl("/failer.jsp")
			.permitAll()
			// 指定退出认证配置
			.and()
			.logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/login.jsp")
			.invalidateHttpSession(true)
			.permitAll()
			// csrf配置
			.and()
			.csrf()
			.disable();

	}

}
