package com.arahansa.cropimageupload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class CropimageuploadApplication {


//	@Bean
//	public CommonsMultipartResolver multipartResolver() {
//		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
//		resolver.setDefaultEncoding("utf-8");
//		resolver.setMaxUploadSize(-1);
//		return resolver;
//	}

	public static void main(String[] args) {
		SpringApplication.run(CropimageuploadApplication.class, args);
	}
}
