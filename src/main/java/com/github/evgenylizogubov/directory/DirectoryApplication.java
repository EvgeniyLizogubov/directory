package com.github.evgenylizogubov.directory;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        servers = {@Server(url = "/", description = "Default Server URL")},
        info = @Info(
                title = "REST Api documentation",
                description = """
                        Реализация REST API приложения для справочника компаний, зданий, рубрик.
                        
                        ##### Координаты зданий:
                        
                        - id: 1 (111, 111);
                        - id: 2 (222, 222);
                        - id: 3 (333, 333).
                        
                        ##### Структура рубрик следующая:
                        
                        - Напитки (id: 1)
                        	- Газированные (id: 2)
                        		- Квасы (id: 3)
                        			- Живые (id: 4)
                        			- Неживые (id: 5)
                        - Еда (id: 6)
                        	- Полуфабрикаты оптом (id: 7)
                        	- Мясная продукция (id: 8)
                        - Автомобили (id: 9)
                        	- Грузовые (id: 10)
                        	- Легковые (id: 11)
                        		- Запчасти для подвески (id: 12)
                        		- Шины/диски (id: 13)
                        """
        ),
        externalDocs = @ExternalDocumentation()
)
public class DirectoryApplication {
    public static void main(String[] args) {
        SpringApplication.run(DirectoryApplication.class, args);
    }
}
