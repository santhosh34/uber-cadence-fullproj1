package io.sannette.eis.mega;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@SpringBootApplication
public class CustomerOnboardingApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerOnboardingApplication.class, args);
	}

}
