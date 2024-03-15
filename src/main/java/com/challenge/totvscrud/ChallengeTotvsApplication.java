package com.challenge.totvscrud;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@OpenAPIDefinition(info =
@Info(
		title = "Desafio TOTVS",
		version = "1.0.0",
		description = "Desafio TOTVS"
)
)
@ComponentScan("com.challenge")
@SpringBootApplication
public class ChallengeTotvsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeTotvsApplication.class, args);
	}

}
