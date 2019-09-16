package cristiano.estudos.micronaut.spring.data.services.service;

import cristiano.estudos.micronaut.spring.data.services.domain.Movie;
import cristiano.estudos.micronaut.spring.data.services.exception.MovieNotFoundException;
import cristiano.estudos.micronaut.spring.data.services.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private MovieRepository repository;

    private Logger log = LoggerFactory.getLogger(MovieService.class);

    private boolean isInitialized = false;

    @Autowired
    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void createMovie(List<Movie> movies) {
        repository.createMovies(movies);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateMovie(Movie movie) {
        if(movie.getId() == null || movie.getId() < 0) {
            throw new IllegalArgumentException("Invalid value for ID");
        }
        repository.updateMovie(movie);
    }

    public Movie getMovie(Integer idMovie) throws MovieNotFoundException {
        Optional<Movie> dbMovie = repository.getMovie(idMovie);
        return dbMovie.orElseThrow(() -> new MovieNotFoundException());
    }

    public List<Movie> listAll() {
        List<Movie> movies = repository.listAllMovies();

        return movies;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void initialize(){
        if (isInitialized) {
            return;
        }
        repository.createTable();

        repository.createMovies(Arrays.asList(
                new Movie("Matrix", new Date()),
                new Movie("Galinha Pintadinha", new Date()),
                new Movie("Vingadores: End Game", new Date()),
                new Movie("Titanic", new Date()),
                new Movie("Matrix: Revolutions", new Date()),
                new Movie("Tropa de Elite", new Date())
        ));
    }

}
