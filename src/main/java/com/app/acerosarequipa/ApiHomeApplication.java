package com.app.acerosarequipa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
@MapperScan("com.app.acerosarequipa.mappers")
public class ApiHomeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiHomeApplication.class, args);
	}

}