package com.itheima;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.itheima.config.RsaKeyProperties;

@SpringBootApplication
@MapperScan("com.itheima.mapper")
@EnableConfigurationProperties(RsaKeyProperties.class)
public class AuthServerApplication {
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(AuthServerApplication.class, args);
	}

}
