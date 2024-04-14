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



/***
 *
 *insert into customer (id, email, name) values (1, 'abc@gmail.com', 'abc tan');
 * insert into wallet(id, customer_id, balance) values (1, 1, 1050000)
 * update wallet set balance=50000 where id=1
 *
 * select * from price_agg;
 * select * from customer;
 * select * from wallet;
 * select * from trade;
 * select * from cryto_type
 *
 *
 */