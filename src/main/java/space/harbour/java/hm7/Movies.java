package space.harbour.java.hm7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Movies {
    public String title;
    public double rating;
    public int year;
    public int runtime;
    public String[] genres;
    public String director;
    public String[] actors;

    public Movies(String title, double rating, int year,
                           int runtime, String[] genres, String director, String[] actors) {
        this.title = title;
        this.rating = rating;
        this.year = year;
        this.runtime = runtime;
        this.genres = genres;
        this.director = director;
        this.actors = actors;
    }

    @Override
    public String toString() {
        return "MoviesList{" + "title='" + title + '\''
                + ", rating=" + rating
                + ", year=" + year + ", runtime=" + runtime
                + ", genres=" + Arrays.toString(genres)
                + ", director='" + director + '\''
                + ", actors=" + Arrays.toString(actors)
                + '}';
    }

    public double getRating() {
        return rating;
    }

    public String getTitle() {
        return title;
    }

    public int getRuntime() {
        return runtime;
    }

    public int getYear() {
        return year;
    }

    public String getDirector() {
        return director;
    }

    public String[] getActors() {
        return actors;
    }

    public String[] getGenres() {
        return genres;
    }

    public static void main(String[] args) {
        ArrayList<Movies> movies = new ArrayList<>();

        Movies movie = new Movies("Movie 1", 9.7, 2010,
                210, new String[]{"adventure", "action"}, "Ahmed Manji",
                new String[]{"Alex", "Alejandro"});
        movies.add(movie);

        Movies movie2 = new Movies("Movie 2", 7.0, 2014,
                150, new String[]{"adventure"}, "Vasili",
                new String[]{"Mohammed", "Salim"});
        movies.add(movie2);

        Movies movie3 = new Movies("Movie 3", 8.0, 2018,
                90, new String[]{"adventure", "action"}, "Abdullah",
                new String[]{"John", "Jimmy", "Alex"});
        movies.add(movie3);


        /// SORT By year
        System.out.println("\nSorting by year: ");
        Collections.sort(movies, Comparator.comparingInt(y -> y.year));
        movies.stream().forEach(mov -> System.out.println(mov));

        /// SORT By ratings
        System.out.println("\nSorting by ratings: ");
        Collections.sort(movies, Comparator.comparingDouble(r -> r.rating));
        movies.stream().forEach(mov -> System.out.println(mov));

        /// SORT By runtime
        System.out.println("\nSorting by runtime: ");
        Collections.sort(movies, Comparator.comparingInt(r -> r.runtime));
        movies.stream().forEach(mov -> System.out.println(mov));

        /// Filter by Director
        System.out.println("\nFilter by Director: ");
        ArrayList<Movies> filteredByDirectors = (ArrayList<Movies>) movies.stream()
                .filter(mov -> mov.getDirector().contains("Abdullah"))
                .collect(Collectors.toList());
        filteredByDirectors.stream().forEach(mov -> System.out.println(mov.getTitle()));

        System.out.println("\nFilter by Genres: ");
        /// Filter by Generes
        ArrayList<Movies> filteredByGenres = (ArrayList<Movies>) movies.stream()
                .filter(mov -> Arrays.stream(mov.getGenres()).anyMatch(g -> g == "action"))
                .collect(Collectors.toList());
        filteredByGenres.stream().forEach(mov -> System.out.println(mov.getTitle()));

        System.out.println("\nFilter by Actors: ");
        ArrayList<Movies> filteredByActors = (ArrayList<Movies>) movies.stream()
                .filter(mov -> Arrays.stream(mov.getActors()).anyMatch(a -> a == "Alex"))
                .collect(Collectors.toList());
        filteredByActors.stream().forEach(mov -> System.out.println(mov.getTitle()));

    }
}
