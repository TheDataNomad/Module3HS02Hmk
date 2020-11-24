package space.harbour.java.hm12;

import java.util.Arrays;

public class Movie {

    public Movie(String title, double rating,
                 int year, String runtime,
                 String[] genres, String director,
                 String[] actors, String image) {
        this.title = title;
        this.rating = rating;
        this.year = year;
        this.runtime = runtime;
        this.genres = genres;
        this.director = director;
        this.actors = actors;
        this.image = image;
    }


    public String title;
    public double rating;
    public int year;
    public String runtime;
    public String[] genres;
    public String director;
    public String[] actors;
    public String image;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String[] getActors() {
        return actors;
    }

    public void setActors(String[] actors) {
        this.actors = actors;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Movie{"
                + "title='" + title + '\''
                + ", rating=" + rating
                + ", year=" + year
                + ", runtime=" + runtime
                + ", genres=" + Arrays.toString(genres)
                + ", director='" + director + '\''
                + ", actors=" + Arrays.toString(actors)
                + ", image='" + image + '\'' + '}';
    }
}
