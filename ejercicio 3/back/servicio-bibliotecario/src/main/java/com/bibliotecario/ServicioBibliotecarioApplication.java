package com.bibliotecario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan({"com.commons"})
public class ServicioBibliotecarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioBibliotecarioApplication.class, args);
	}

}
