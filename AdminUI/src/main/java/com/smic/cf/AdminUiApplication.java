package com.smic.cf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdminUiApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminUiApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(AdminUiApplication.class, args);
		logger.info("项目启动了。。。。。。。。。。。。。");
	}

}
