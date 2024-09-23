package com.alexis.demo.webserviceproject_v2.model;

import jakarta.persistence.*;
/**JSON format för enklare testning:
{
  "review": "skriv recensionen här",
  "rating": rating,
  "movie": {
      "id": Den id som filmen är sparat som
    }
}
*/
@Entity
public class MovieReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String review;
    private int rating;
    @JoinColumn(name = "movie_id", nullable = false)
    @ManyToOne
    private Movie movie;

    public MovieReview(){}

    public MovieReview(Movie movie,String review,int rating){
        this.movie=movie;
        this.review=review;
        this.rating=rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
