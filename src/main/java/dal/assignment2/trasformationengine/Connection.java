package dal.assignment2.trasformationengine;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.io.IOException;

public interface Connection {
    MongoCollection<Document> establishingConnectionWithDatabase(String collectionName);
    void insertDataIntoTheDatabase(MongoCollection<Document> collection,String directoryPath) throws IOException;
}
