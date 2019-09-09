package cristiano.estudos.micronaut.spring.data.services;

import io.micronaut.runtime.Micronaut;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class Application {

	public static void main(String[] args) {
		Micronaut.run(Application.class, args);
		//SpringApplication.run(Application.class, args);
	}

}
