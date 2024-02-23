package com.rentu.rentu;

import com.rentu.rentu.services.EmailService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;


@SpringBootApplication
@ComponentScan(basePackages = {"com.rentu.rentu", "com.rentu.rentu.dao"})
public class RentuApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentuApplication.class, args);
	}

}