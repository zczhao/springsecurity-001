package com.itheima.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// 认证用户来源【内存或数据库】
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("user")
			.password("{noop}" + "123456")
			.roles("USER"); // 配置类中不能加ROLE_，Spring Security会默认加上
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
