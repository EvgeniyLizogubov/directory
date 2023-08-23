package com.github.EvgenyLizogubov.directory;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
		title = "REST Api documentation",
		description = "Реализация REST API приложения для справочника компаний, зданий, рубрик."
))
public class DirectoryApplication {
	public static void main(String[] args) {
		SpringApplication.run(DirectoryApplication.class, args);
	}

}
