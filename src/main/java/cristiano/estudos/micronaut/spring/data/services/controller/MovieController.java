package cristiano.estudos.micronaut.spring.data.services.controller;

import cristiano.estudos.micronaut.spring.data.services.domain.Movie;
import cristiano.estudos.micronaut.spring.data.services.exception.MovieNotFoundException;
import cristiano.estudos.micronaut.spring.data.services.service.MovieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api("Crud basico para o dominio 'Filme'")
@RestController
@RequestMapping("/v1/movies")
public class MovieController {

    private MovieService service;

    @Autowired
    public MovieController(MovieService service) {
        this.service = service;
    }

    @ApiOperation("Criar um ou mais Filmes")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Filme criado com sucesso")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody List<MovieResource> resources) {
        service.createMovie(resources.stream().map(this::toDomain).collect(Collectors.toList()));
    }

    @ApiOperation("Atualizar um Filme pelo Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Atualização realizada com sucesso"),
            @ApiResponse(code = 400, message = "Id não informado")
    })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") Integer id, @RequestBody MovieResource resource) {
        resource.id = id;
        Movie movie = toDomain(resource);
        service.updateMovie(movie);
    }

    @ApiOperation("Obtem os dados de uma entidade pelo Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Filme encontrado", response = MovieResource.class),
            @ApiResponse(code = 404, message = "Filme não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<MovieResource> get(@PathVariable("id") Integer id) throws MovieNotFoundException {
        return ResponseEntity.ok(toResource(service.getMovie(id)));
    }

    @ApiOperation("Lista todos os filmes")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Filme encontrado", response = MovieResource[].class),
            @ApiResponse(code = 404, message = "Filme não encontrado")
    })
    @GetMapping
    public ResponseEntity<List> list() {
        return ResponseEntity.ok(service.listAll().stream().map(this::toResource).collect(Collectors.toList()));
    }

    @ApiOperation("Inicializa o DB")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Carga efetuada com sucesso"),
    })
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/initialize")
    public void initialize() {
        service.initialize();
    }

    private Movie toDomain(MovieResource resource) {
        return new Movie(resource.id, resource.title, resource.releaseDate);
    }

    private MovieResource toResource(Movie movie) {
        return new MovieResource(movie.getId(), movie.getTitle(), movie.getRelease());
    }

}
