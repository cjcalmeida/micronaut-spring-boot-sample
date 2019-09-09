package cristiano.estudos.micronaut.spring.data.services.repository;

import cristiano.estudos.micronaut.spring.data.services.domain.Movie;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MovieMapper implements RowMapper<Movie> {

    @Override
    public Movie map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Movie(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getDate("release_date"));
    }
}
