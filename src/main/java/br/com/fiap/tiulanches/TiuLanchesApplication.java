package br.com.fiap.tiulanches;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(title="API Tiu Lanches", version = "23.12.04", description = "Tech Challenge para conclusão Pós Graduação Software Architecture pela FIAP"),
		servers = { @Server(url="http://localhost:8080") }
)
public class TiuLanchesApplication {	
	public static void main(String[] args) {
		SpringApplication.run(TiuLanchesApplication.class, args);
	}

}
