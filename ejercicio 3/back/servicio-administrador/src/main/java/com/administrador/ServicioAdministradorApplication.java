package com.administrador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan({"com.commons"})
@SpringBootApplication
public class ServicioAdministradorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioAdministradorApplication.class, args);
	}

}
