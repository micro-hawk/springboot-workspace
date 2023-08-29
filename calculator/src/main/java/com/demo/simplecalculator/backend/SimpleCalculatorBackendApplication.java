package com.demo.simplecalculator.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SimpleCalculatorBackendApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(SimpleCalculatorBackendApplication.class, args);
	}
        
        @Override
        protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
            return builder.sources(SimpleCalculatorBackendApplication.class);
        }

}
