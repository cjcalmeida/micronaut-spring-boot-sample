package cristiano.estudos.micronaut.spring.data.services.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Movie not found")
public class MovieNotFoundException extends Exception {
}
