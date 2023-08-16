package com.gbhu.xdkt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.gbhu.xdkt.mapper")
@EnableTransactionManagement
//@ServletComponentScan
//@EnableScheduling
@EnableAsync
public class XdktApplication {

	public static void main(String[] args) {
		SpringApplication.run(XdktApplication.class, args);
	}





}
