package com.fastforward.cardsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class CardsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardsApiApplication.class, args);
	}

}
