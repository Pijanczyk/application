package com.photoexchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class PhotoexchangeApplication extends SpringBootServletInitializer  {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(PhotoexchangeApplication.class);
    }
	
	public static void main(String[] args) {
		SpringApplication.run(PhotoexchangeApplication.class, args);
	}
}
