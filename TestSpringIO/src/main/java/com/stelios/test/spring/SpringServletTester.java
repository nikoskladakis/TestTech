package com.stelios.test.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan( basePackages = {
		 "com.stelios.test.spring"
})
public class SpringServletTester extends SpringBootServletInitializer {
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringServletTester.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringServletTester.class, args);
    }
}
