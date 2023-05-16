package dal.assignment2;

import com.mongodb.client.MongoCollection;
import dal.assignment2.dataprocessing.FileHandling;
import dal.assignment2.dataprocessing.FileOperations;
import dal.assignment2.dataprocessing.Processing;
import dal.assignment2.dataprocessing.ProcessingData;
import dal.assignment2.extraction.Extract;
import dal.assignment2.extraction.ExtractionEngine;
import dal.assignment2.trasformationengine.ConnectToDataBase;
import dal.assignment2.trasformationengine.Connection;
import org.bson.Document;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Runner implements Runnable {

    private String topicName;
    private String directoryName;
    private String fileName;
    private String url;

    public Runner(String topicName) {
        this.topicName = replaceTopicName(topicName, Constants.TOPIC_NAME);
        this.directoryName = replaceTopicName(topicName, Constants.DIRECTORY);
        this.fileName = replaceTopicName(topicName, Constants.FILE_NAME);
        this.url = replaceTopicName(topicName, Constants.URL);
    }


    /***
     * This method is where the main executions take place, all the method calling happen here, We call all the methods
     * in the run method because of executing in parallel requires thread.
     * At, first we call data extraction phase and then filtering and processing phase and then finally the
     * transformation part where we upload into the mango db.
     */
    @Override
    public void run() {

        Extract extractionEngine = new ExtractionEngine();
        Processing processingData = new ProcessingData();
        try {

            String extractedDate = extractionEngine.extractingData(url);
            List<Map<String, String>> listOfTitleAndContent = processingData.getTitleAndContent(extractedDate);
            System.out.println("Phase 1 done");
            FileOperations fileHandling = new FileHandling();
            fileHandling.writeFile(listOfTitleAndContent, fileName, directoryName);
            System.out.println("Phase 2 done");
            Connection connectToDataBase = new ConnectToDataBase();
            MongoCollection<Document> collection = connectToDataBase.establishingConnectionWithDatabase(topicName);
            System.out.println("DB connect phase 3");
            connectToDataBase.insertDataIntoTheDatabase(collection,directoryName);
            System.out.println("Phase 3 done");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /***
     * This method is just used in referencing the constructor values with topic name instead of creating <topic> everytime.
     * @param topicName
     * @param data
     * @return
     */
    private String replaceTopicName(String topicName, String data) {
        return data.replace("<topic>", topicName);
    }
}
