package cristiano.estudos.micronaut.spring.data.services.configuration;

import cristiano.estudos.micronaut.spring.data.services.repository.MovieRepository;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.spi.JdbiPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import javax.servlet.ServletContext;
import javax.sql.DataSource;
import java.util.List;

@Configuration
public class AppConfiguration {

    @Bean
    public Jdbi jdbi(DataSource dataSource, List<JdbiPlugin> plugins, List<RowMapper> rowMappers){
        TransactionAwareDataSourceProxy proxy = new TransactionAwareDataSourceProxy(dataSource);
        Jdbi jdbi = Jdbi.create(proxy);
        plugins.forEach(jdbiPlugin -> jdbi.installPlugin(jdbiPlugin));
        rowMappers.forEach(rowMapper -> jdbi.registerRowMapper(rowMapper));

        return jdbi;
    }

    @Bean
    public JdbiPlugin sqlObjectPlugin(){
        return new SqlObjectPlugin();
    }

    @Bean
    public MovieRepository movieRepository(Jdbi jdbi){
        return jdbi.onDemand(MovieRepository.class);
    }

    @Bean
    public Docket api(ServletContext servletContext){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                    .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                    .paths(PathSelectors.any())
                    .build()
                .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                .enableUrlTemplating(true)
                .apiInfo(new ApiInfoBuilder()
                    .description("API de operações basicas da entidade Movie (Filme)")
                .title("Movie Api")
                .version("1.0.0")
                .build());
    }

}
