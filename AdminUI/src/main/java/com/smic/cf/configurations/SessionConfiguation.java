package com.smic.cf.configurations;

import javax.annotation.Resource;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.smic.cf.filter.AdminInterceptor;


@SpringBootConfiguration
@SuppressWarnings("deprecation")
public class SessionConfiguation extends WebMvcConfigurerAdapter {

	@Resource
	AdminInterceptor adminInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		 
		registry.addInterceptor(adminInterceptor).addPathPatterns("/tgls/**","/admin/**").excludePathPatterns("/","/tgls/iconfont.html");
	}

	

}
