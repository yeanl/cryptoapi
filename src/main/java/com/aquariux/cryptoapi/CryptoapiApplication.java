package com.aquariux.cryptoapi;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CryptoapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptoapiApplication.class, args);
	}

	@Bean //Make DTO mapping by using 3rd party library
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}
