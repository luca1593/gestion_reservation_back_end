package com.detech.gsrt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@ComponentScan
@SpringBootApplication
@EnableJpaAuditing
public class GsrtApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(GsrtApplication.class, args);
	}

}
