package cristiano.estudos.micronaut.spring.data.services.domain;

import java.util.Date;

public class Movie {

    private Integer id;
    private String title;
    private Date release;

    public Movie(String title, Date release) {
        this.title = title;
        this.release = release;
    }

    public Movie(Integer id) {
        this.id = id;
    }

    public Movie(Integer id, String title, Date releaseDate) {
        this.id = id;
        this.title = title;
        this.release = releaseDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getRelease() {
        return release;
    }

    public void setRelease(Date releaseDate) {
        this.release = releaseDate;
    }
}
