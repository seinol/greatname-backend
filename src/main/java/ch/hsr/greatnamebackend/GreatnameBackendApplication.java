package ch.hsr.greatnamebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class GreatnameBackendApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(GreatnameBackendApplication.class, args);
	}

}
