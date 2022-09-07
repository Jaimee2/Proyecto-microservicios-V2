package cochesservice.cochesservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CochesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CochesServiceApplication.class, args);
		System.out.println("Microservice coches RUNIG");
	}

}
