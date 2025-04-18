package com.dite.znpt.web;

import cn.hutool.extra.spring.EnableSpringUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.dite.**.mapper")
@ComponentScan(basePackages = {"com.dite"})
@EnableSpringUtil
public class DiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiteApplication.class, args);
	}

}
