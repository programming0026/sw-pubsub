package com.juniverse.pubsub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** Ejemplo de publicación y suscripción con Google Cloud Pub/Sub y Spring Boot (WebFlux). */
@SpringBootApplication
public class DemoApplication {

	/**
	 * Arranca la aplicación.
	 * @param args argumentos de la línea de comandos
	 */
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
