package com.dite.znpt;

import cn.hutool.extra.spring.EnableSpringUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.dite.znpt.mapper")
@EnableSpringUtil
public class DiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiteApplication.class, args);
	}

}
