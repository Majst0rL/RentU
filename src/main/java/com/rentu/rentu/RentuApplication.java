package com.rentu.rentu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = {"com.rentu.rentu", "com.rentu.rentu.dao"})
public class RentuApplication {
	public static void main(String[] args) {
		SpringApplication.run(RentuApplication.class, args);
	}
}
