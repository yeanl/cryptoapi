package com.aquariux.cryptoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CryptoapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptoapiApplication.class, args);
	}

}
