package com.lector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EntityScan({"com.commons.models"})
@EnableFeignClients
public class ServicioLectorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioLectorApplication.class, args);
	}

}
