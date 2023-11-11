package com.devluis.mscompcourse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsCompCourseApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCompCourseApplication.class, args);
	}

}
