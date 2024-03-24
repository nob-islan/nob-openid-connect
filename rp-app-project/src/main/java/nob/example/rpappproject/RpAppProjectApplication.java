package nob.example.rpappproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Relying Party API", version = "1.0.0", description = "リライングパーティの機能を提供するAPIです。"))
public class RpAppProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(RpAppProjectApplication.class, args);
	}
}
