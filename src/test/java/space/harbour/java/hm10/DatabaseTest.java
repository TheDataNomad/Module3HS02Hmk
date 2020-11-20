package space.harbour.java.hm10;

import com.mongodb.BasicDBObject;
import java.util.function.Function;
import org.bson.Document;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DatabaseTest {

    public static BasicDBObject searchQuery = new BasicDBObject();
    public static Function<Document, String> handler;
    MongoExecutor executor = new MongoExecutor();
    Document movie = new Document("name", "Test Movie")
            .append("runtime", 404)
            .append("year", 2020)
            .append("genre", "drama");

    @Before
    public void init() {
        executor.execStoreMovie(movie);
    }

    @After
    public void afterTest() {
        executor.execRemoveMovie(movie);
    }

    @Test
    public void movieAddedandRetrievedCorrectly() {
        searchQuery.put("name", "Test Movie");
        handler = s -> String.valueOf(s);

        String result = executor.execFindOne("movies", searchQuery, handler);

        Assert.assertTrue(result.contains("Test Movie"));
        Assert.assertTrue(result.contains("drama"));
    }

}
