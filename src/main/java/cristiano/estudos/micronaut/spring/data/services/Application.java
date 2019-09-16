package cristiano.estudos.micronaut.spring.data.services;

import cristiano.estudos.micronaut.spring.data.services.service.MovieService;
import io.micronaut.runtime.Micronaut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class Application {

	@Autowired
	public MovieService service;

	public static void main(String[] args) {
		Micronaut.run(Application.class, args);
		//SpringApplication.run(Application.class, args);
	}

	@Bean
	public ApplicationRunner runner (){
		return args -> {
			service.initialize();
		};
	}

}
