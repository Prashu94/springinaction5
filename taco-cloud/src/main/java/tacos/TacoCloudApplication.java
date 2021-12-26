package tacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @SpringBootApplication
 * - Annotation signifies a Spring Boot Application
 * - comprises of other three annotations: 
 * 1. @SpringBootConfiguration
 * - designates the class as a configuration class.
 * 2. @EnableAutoConfiguration
 * - enables Spring Boot AutoConfiguration
 * 3. @ComponentScan
 * - Enables component scanning.
 * **/
@SpringBootApplication
public class TacoCloudApplication {

	public static void main(String[] args) {
		/**Performs bootstrapping of the application, creating Spring application
		 * context.**/
		SpringApplication.run(TacoCloudApplication.class, args);
	}

}
