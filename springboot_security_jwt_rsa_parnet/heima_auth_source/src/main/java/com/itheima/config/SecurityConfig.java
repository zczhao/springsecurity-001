package com.itheima.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import com.itheima.filter.JwtVerifyFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private RsaKeyProperties prop;
	
	// 配置SpringSecurity相关信息
	@Override
	public void configure(HttpSecurity http) throws Exception {
		// 释放静态资源、指定资源拦截规则、指定自定义认证页面、指定退出认证配置、csrf配置
		http
			// csrf配置
			.csrf()
			.disable()	
			.authorizeRequests()
			// 指定资源拦截规则
			.antMatchers("/**").hasAnyRole("USER","ADMIN")
			.anyRequest().authenticated()
			.and()
			.addFilter(new JwtVerifyFilter(super.authenticationManager(), prop))
			// 禁用 session
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

}
