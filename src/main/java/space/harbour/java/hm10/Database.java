package space.harbour.java.hm10;


import com.mongodb.BasicDBObject;
import java.util.function.Function;
import org.bson.Document;

public class Database {
    public static BasicDBObject searchQuery = new BasicDBObject();
    public static Function<Document, String> handler;

    public static void main(String[] args) {

        MongoExecutor executor = new MongoExecutor();
        Document movie = new Document("name", "Java")
                .append("runtime", 404)
                .append("year", 2020)
                .append("genre", "horror");

        //executor.execStoreMovie(movie);

        searchQuery.put("name", "Java");
        handler = s -> String.valueOf(s);

        String result = executor.execFindOne("movies", searchQuery, handler);
        System.out.println(result);
    }
}
