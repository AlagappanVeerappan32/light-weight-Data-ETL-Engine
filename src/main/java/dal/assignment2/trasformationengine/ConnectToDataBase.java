package dal.assignment2.trasformationengine;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import dal.assignment2.Constants;
import org.bson.Document;

import java.io.IOException;
import java.util.List;

public class ConnectToDataBase implements Connection{


    /***
     * This method is used to create the mongoDB connection and create collection,
     * it is the setup process for the database.
     * @param collectionName collection name which will be the keyword
     * @return returns a collection
     */
    @Override
    public  MongoCollection<Document> establishingConnectionWithDatabase(String collectionName) {

        ConnectionString connectionString = new ConnectionString(Constants.CONNECTION_STRING);
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        MongoClient mongoClient = MongoClients.create(settings);
        MongoDatabase database = mongoClient.getDatabase(Constants.DATABASE_NAME);
        MongoCollection<Document> collection = database.getCollection(collectionName);
        return collection;
    }

    /***
     * This method calls the readfile method which has the desired title and content from the files, these will be
     * inserted into the database using the insertOne method.
     * @param collection
     * @param directoryPath
     * @throws IOException
     */
    @Override
    public void insertDataIntoTheDatabase(MongoCollection<Document> collection,String directoryPath) throws IOException {
        ReadFiles readFiles = new ReadFiles();
        List<Document> listOfData = readFiles.readTheFiles(directoryPath);
        for (Document document : listOfData) {
            collection.insertOne(document);
        }
    }







}
