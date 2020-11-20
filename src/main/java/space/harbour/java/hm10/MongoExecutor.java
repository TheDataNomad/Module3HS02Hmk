package space.harbour.java.hm10;


import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.function.Function;
import org.bson.Document;

public class MongoExecutor {
    MongoDatabase mongoDatabase;
    MongoClient mongoClient;

    public MongoExecutor() {
        MongoClientURI uri = new MongoClientURI(
                "mongodb+srv://test:test@cluster0.wtq2z."
                        + "mongodb.net/<moviedb>?retryWrites=true&w=majority");

        mongoClient = new MongoClient(uri);
        mongoDatabase = mongoClient.getDatabase("moviesdb");
    }

    public <T> T execFindOne(String collection, BasicDBObject searchQuery,
                             Function<Document, T> handler) {
        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(collection);
        FindIterable<Document> result = mongoCollection.find(searchQuery);
        return handler.apply(result.first());
    }


    public void execStoreMovie(Document document) {
        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("movies");
        mongoCollection.insertOne(document);
    }

    public void execRemoveMovie(Document document) {
        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("movies");
        mongoCollection.deleteOne(document);
    }

}
