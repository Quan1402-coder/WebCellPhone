package com.charles.website;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@SpringBootApplication
//@ComponentScan(basePackages = "com.charles.website.config")
public class WebsiteApplication {

	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setDefaultEncoding("utf-8");
//        resolver.setMaxUploadSizePerFile(10240000);
		multipartResolver.setMaxInMemorySize(4096);
		multipartResolver.setMaxUploadSize(10 * 1024 * 1024); // 10 MB
		return multipartResolver;
	}

	public static void main(String[] args) {
		SpringApplication.run(WebsiteApplication.class, args);
	}

}
