package space.harbour.java.hm12;



import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;
import static spark.Spark.staticFileLocation;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.Function;
import freemarker.template.Configuration;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bson.Document;
import org.eclipse.jetty.http.HttpStatus;
import spark.ModelAndView;
import spark.Request;
import spark.template.freemarker.FreeMarkerEngine;




public class MovieLibrary {

    public MovieLibrary() throws UnknownHostException {
    }

    public static void main(String[] args) {
        final Gson gson = new Gson();
        MongoExecutor executor = new MongoExecutor();

        //executor.addMovie(new Movie("The Shawshank Redemption",
        //        9.2, 1994,
        //        "2h 22min",
        //        new String[]{"Drama"},
        //        "Frank Darabont",
        //        new String[]{"Tim Robbins", "Morgan Freeman"},
        //        "https://m.media-amazon.com/images/"
        //                + "M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1Z"
        //                + "mRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU"
        //                + "@._V1_UX182_CR0,0,182,268_AL_.jpg"));
        //
        //executor.addMovie(new Movie("The Godfather",
        //        9.2, 1972,
        //        "2h 55min",
        //        new String[]{"Drama", "Crime"},
        //        "Francis Ford Coppola",
        //        new String[]{"Marlon Brando", "Al Pacino", "James Caan"},
        //        "https://m.media-amazon.com/images/"
        //                + "M/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3Z"
        //                + "Tk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@."
        //                + "_V1_UY1200_CR107,0,630,1200_AL_.jpg"));


        staticFileLocation("public");

        post("/movies", (request, response) -> {
            Movie movie = gson.fromJson(request.body(), Movie.class);
            executor.addMovie(movie);
            return "Successfuly added!";
        });

        // Gets the movie resource for the provided id
        get("/movies/:title", (request, response) -> {
            String title = request.params(":title");
            Function<Document, Movie> handler = doc -> gson.fromJson(doc.toJson(), Movie.class);
            Movie movie = executor.getMovieByTitle(title, handler);

            if (movie == null) {
                response.status(HttpStatus.NOT_FOUND_404);
                return "Movie not found";
            }
            if (clientAcceptsHtml(request)) {
                Map<String, Object> movieMap = new HashMap<>();
                movieMap.put("movie", movie);
                return render(movieMap, "movie.ftl");
            } else if (clientAcceptsJson(request)) {
                return gson.toJson(movie);
            }
            return null;
        });


        put("/movies/:title", (request, response) -> {
            String title = request.params(":title");

            BasicDBObject newData = new BasicDBObject();
            String newTitle = request.queryParams("title");

            Function<Document, Movie> handler = doc -> gson.fromJson(doc.toJson(), Movie.class);
            Movie movie = executor.getMovieByTitle(title, handler);

            if (movie == null) {
                response.status(HttpStatus.NOT_FOUND_404);
                return "Movie not found";
            }
            if (newTitle != null) {
                newData.put("title", newTitle);
                executor.updateMovieByTitle(title, newData, handler);
            }
            return "Movie with title '" + title + "' updated";
        });


        delete("/movies/:title", (request, response) -> {
            String title = request.params(":title");
            Function<Document, Movie> handler = doc -> gson.fromJson(doc.toJson(), Movie.class);
            Movie movie = executor.getMovieByTitle(title, handler);
            if (movie == null) {
                response.status(HttpStatus.NOT_FOUND_404);
                return "Movie not found";
            }
            executor.deleteMovieByTitle(title);
            return "Movie with title '" + title + "' deleted";
        });


        get("/movies", (request, response) -> {
            Function<List<Movie>, List<Movie>> handler = x -> x;
            List<Movie> movies = executor.getMovies(handler);
            if (clientAcceptsHtml(request)) {
                Map<String, Object> moviesMap = new HashMap<>();
                moviesMap.put("movies", movies);
                System.out.println(moviesMap);
                return render(moviesMap, "movies.ftl");
            } else if (clientAcceptsJson(request)) {
                return gson.toJson(movies);
            }

            return null;
        });
    }

    public static String render(Map values, String template) {
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine();
        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(MovieLibrary.class, "/spark.template.freemarker/");
        freeMarkerEngine.setConfiguration(configuration);

        return freeMarkerEngine.render(new ModelAndView(values, template));
    }

    public static boolean clientAcceptsHtml(Request request) {
        String accept = request.headers("Accept");
        return accept != null && accept.contains("text/html");
    }

    public static boolean clientAcceptsJson(Request request) {
        String accept = request.headers("Accept");
        return accept != null && (accept.contains("application/json") || accept.contains("*/*"));
    }

}
