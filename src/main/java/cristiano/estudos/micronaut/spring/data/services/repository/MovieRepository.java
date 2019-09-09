package cristiano.estudos.micronaut.spring.data.services.repository;

import cristiano.estudos.micronaut.spring.data.services.domain.Movie;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.SqlBatch;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.Optional;

@UseClasspathSqlLocator
public interface MovieRepository {

    @SqlBatch("create")
    void createMovies(@BindBean List<Movie> movie);

    @SqlUpdate("update")
    void updateMovie(@BindBean Movie movie);

    @SqlQuery("get")
    Optional<Movie> getMovie(@Bind("id") Integer id);

    @SqlQuery("list")
    List<Movie> listAllMovies();

    @SqlUpdate("table")
    void createTable();
}
