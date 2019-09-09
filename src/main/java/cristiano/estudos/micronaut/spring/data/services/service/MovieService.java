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
import org.springframework.util.StopWatch;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private MovieRepository repository;

    private Logger log = LoggerFactory.getLogger(MovieService.class);

    private StopWatch timer = new StopWatch();

    private boolean isInitialized = false;

    @Autowired
    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void createMovie(List<Movie> movies) {
        timer.start("create-query");
        repository.createMovies(movies);
        timer.stop();
        log.info("Tempo gasto na execução da query {}: {}ms", timer.getLastTaskName(), timer.getLastTaskTimeMillis());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateMovie(Movie movie) {
        if(movie.getId() == null || movie.getId() < 0) {
            throw new IllegalArgumentException("Invalid value for ID");
        }
        timer.start("update-query");
        repository.updateMovie(movie);
        timer.stop();
        log.info("Tempo gasto na execução da query {}: {}ms", timer.getLastTaskName(), timer.getLastTaskTimeMillis());
    }

    public Movie getMovie(Integer idMovie) throws MovieNotFoundException {
        timer.start("get-movie-query");
        Optional<Movie> dbMovie = repository.getMovie(idMovie);
        timer.stop();
        log.info("Tempo gasto na execução da query {}: {}ms", timer.getLastTaskName(), timer.getLastTaskTimeMillis());
        return dbMovie.orElseThrow(() -> new MovieNotFoundException());
    }

    public List<Movie> listAll() {
        timer.start("list-query");
        List<Movie> movies = repository.listAllMovies();
        timer.stop();
        log.info("Tempo gasto na execução da query {}: {}ms", timer.getLastTaskName(), timer.getLastTaskTimeMillis());

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
