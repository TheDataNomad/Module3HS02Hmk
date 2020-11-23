package space.harbour.java.hm11;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import org.bson.Document;

public class MongoExecutor {
    MongoClient client;
    MongoDatabase database;
    MongoClientURI uri;
    String collectionName;

    public MongoExecutor() {
        uri = new MongoClientURI(
                "mongodb+srv://test:z6jGW6b8oNwMrVYQ@cluster0.wtq2z."
                        + "mongodb.net/<chatsdb>?retryWrites=true&w=majority");
        client = new MongoClient(uri);
        database = client.getDatabase("chatsdb");
        collectionName = "chats";
    }

    public <T> T execFindOne(String collection,
                             BasicDBObject searchQuery,
                             Function<Document, T> handler) {
        MongoCollection<Document> mongoCollection = database.getCollection(collection);
        FindIterable<Document> result = mongoCollection.find(searchQuery);
        return handler.apply(result.first());
    }

    public <T> Set<Document> execGetAllMessages(Function<Set<Document>, Set<Document>> handler) {
        MongoCollection<Document> mongoCollection = database.getCollection(collectionName);
        Set<Document> messages = new HashSet<>();
        FindIterable<Document> cursor = mongoCollection.find();
        for (Document document : cursor) {
            messages.add(document);
        }
        return handler.apply(messages);
    }

    public void execStoreMessage(Document document) {
        MongoCollection<Document> mongoCollection = database.getCollection(collectionName);
        mongoCollection.insertOne(document);
    }
}
