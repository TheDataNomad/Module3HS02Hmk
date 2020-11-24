package space.harbour.java.hm12;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.Function;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;


public class MongoExecutor {
    MongoDatabase database;

    public MongoExecutor() {
        MongoClientURI uri = new MongoClientURI(
                "mongodb+srv://ahmed:7HW3WE1qN5vCEhVD@cluster0.wtq2z.mongodb.net"
                        + "/moviesdb?retryWrites=true&w=majority");
        MongoClient client = new MongoClient(uri);
        database = client.getDatabase("moviesdb");
    }

    public List<Movie> getMovies(Function<List<Movie>, List<Movie>> handler) {
        MongoCollection<Document> mongoCollection = database.getCollection("movies");
        FindIterable<Document> result = mongoCollection.find();
        List<Movie> movies = new ArrayList<>();
        for (Document document : result) {
            movies.add(new Gson().fromJson(document.toJson(), Movie.class));
        }
        return handler.apply(movies);
    }

    public Movie getMovieByTitle(String title, Function<Document, Movie> handler) {
        MongoCollection<Document> mongoCollection = database.getCollection("movies");
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("title", title);
        FindIterable<Document> result = mongoCollection.find(searchQuery);
        return handler.apply(result.first());
    }

    public Movie updateMovieByTitle(String title,
                                 BasicDBObject newData,
                                 Function<Document, Movie> handler) {
        MongoCollection<Document> mongoCollection = database.getCollection("movies");
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("title", title);

        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$set", newData);

        mongoCollection.updateOne(searchQuery, updateObject);
        return handler.apply(mongoCollection.find(searchQuery).first());
    }

    public void addMovie(Movie movie) {
        String json = new Gson().toJson(movie);
        Document doc = Document.parse(json);
        MongoCollection<Document> mongoCollection = database.getCollection("movies");
        mongoCollection.insertOne(doc);
    }

    public void deleteMovieByTitle(String title) {
        MongoCollection<Document> mongoCollection = database.getCollection("movies");
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("title", title);
        mongoCollection.deleteOne(searchQuery);
    }

}
