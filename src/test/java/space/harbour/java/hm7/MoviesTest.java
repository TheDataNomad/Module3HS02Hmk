package space.harbour.java.hm7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MoviesTest {
    ArrayList<Movies> movies = new ArrayList<>();
    public Movies movie;
    public Movies movie2;
    public Movies movie3;

    @Before
    public void setUp() {
        movie = new Movies("Movie 1", 9.7, 2010,
                210, new String[]{"adventure", "action"}, "Ahmed Manji",
                new String[]{"Alex", "Alejandro"});
        movies.add(movie);

        movie2 = new Movies("Movie 2", 7.0, 2014,
                150, new String[]{"adventure"}, "Vasili",
                new String[]{"Mohammed", "Salim"});
        movies.add(movie2);

        movie3 = new Movies("Movie 3", 8.0, 2018,
                90, new String[]{"adventure", "action"}, "Abdullah",
                new String[]{"John", "Jimmy", "Alex"});
        movies.add(movie3);
    }

    // Year Sorting
    @Test
    public void test01() {
        Collections.sort(movies, Comparator.comparingInt(y -> y.year));
        Assert.assertEquals(movies.get(0).title, "Movie 1");
        Assert.assertEquals(movies.get(2).title, "Movie 3");
    }

    // Ratings Sorting
    @Test
    public void test02() {
        Collections.sort(movies, Comparator.comparingDouble(r -> r.rating));
        Assert.assertEquals(movies.get(0).title, "Movie 2");
        Assert.assertEquals(movies.get(2).title, "Movie 1");
    }

    // Runtime Sorting
    @Test
    public void test03() {
        Collections.sort(movies, Comparator.comparingInt(r -> r.runtime));
        Assert.assertEquals(movies.get(0).title, "Movie 3");
        Assert.assertEquals(movies.get(2).title, "Movie 1");
    }

    //Filter by Director
    @Test
    public void test04() {
        ArrayList<Movies> filteredByDirectors = (ArrayList<Movies>) movies.stream()
                .filter(mov -> mov.getDirector().contains("Abdullah"))
                .collect(Collectors.toList());
        Assert.assertTrue(filteredByDirectors.contains(movie3));
        Assert.assertEquals(filteredByDirectors.size(), 1);
    }

    //Filter by Genre
    @Test
    public void test05() {
        ArrayList<Movies> filteredByGenres = (ArrayList<Movies>) movies.stream()
                .filter(mov -> Arrays.stream(mov.getGenres()).anyMatch(g -> g == "action"))
                .collect(Collectors.toList());
        Assert.assertTrue(filteredByGenres.contains(movie3));
        Assert.assertTrue(filteredByGenres.contains(movie));
        Assert.assertFalse(filteredByGenres.contains(movie2));
        Assert.assertEquals(filteredByGenres.size(), 2);
    }

    //Filter by Actor
    @Test
    public void test06() {
        ArrayList<Movies> filteredByActors = (ArrayList<Movies>) movies.stream()
                .filter(mov -> Arrays.stream(mov.getActors()).anyMatch(a -> a == "Alex"))
                .collect(Collectors.toList());
        Assert.assertTrue(filteredByActors.contains(movie));
        Assert.assertTrue(filteredByActors.contains(movie3));
        Assert.assertFalse(filteredByActors.contains(movie2));
        Assert.assertEquals(filteredByActors.size(), 2);
    }





}