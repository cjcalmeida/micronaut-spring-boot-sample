package cristiano.estudos.micronaut.spring.data.services.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class MovieResource {

    public Integer id;
    public String title;
    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date releaseDate;

    public MovieResource(Integer id, String title, Date releaseDate) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
    }
}
