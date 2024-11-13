package com.alexis.demo.webserviceproject_v2.model;

import jakarta.persistence.*;
/**JSON format för enklare testning:
{
    "title": "title",
    "director": "director",
    "genre": "genre",
    "releaseYear": xxxx
}
 */
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String director;
    private String genre;
    private int releaseYear;

    public Movie(){}

    public Movie (String title,String director,String genre,int releaseYear){
        this.title=title;
        this.director=director;
        this.genre=genre;
        this.releaseYear=releaseYear;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

}
