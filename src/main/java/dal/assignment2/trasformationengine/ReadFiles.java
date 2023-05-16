package dal.assignment2.trasformationengine;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;


public class ReadFiles {

    /***
     * This method is used to read the files from the required directory based on the keyword,
     * It first checks the file exist if so, it proceeds further by getting title and content from the file
     * we are using substring to remove the title and content column name from file as it will be passed as a key in
     * database, so instead of repeating the column names, we are removing it using substring,
     * once it is done, we are storing in the list of documents and returning to insert the values.
     *
     * @param directoryPath the path where the data is located
     * @return the list of documents (values from the file to document format values)
     * @throws IOException throws IOException as the file may or may not exist
     */
    public List<Document> readTheFiles(String directoryPath) throws IOException {

        // to store values
        List<Document> documents = new ArrayList<>();

        // creating directory instance
        File directory = new File(directoryPath);

        // getting list of files present in the directory
        File[] files = directory.listFiles();

        // check if files are present in the directory
        if (files == null) {
            System.out.println("the Directory is empty or doesn't exist");
            return null;
        }
        // looping through the files
        for (File eachFile : files) {

            // check file is a file not a directory
            if (eachFile.isFile()) {

                String title = null;
                String content = null;

                BufferedReader bufferedReader = new BufferedReader(new FileReader(eachFile));
                String line =bufferedReader.readLine();

                // until lines are null
                while (line != null) {

                    if (line.startsWith("title=")) {
                        title = line.substring(6);

                    } else if (line.startsWith("content=")) {
                        content = line.substring(8);
                    }

                    if (title != null && content != null) {
                        Document document = new Document("title", title).append("content", content);
                        documents.add(document);
                        title = null;
                        content = null;
                    }

                    line = bufferedReader.readLine();
                }
                bufferedReader.close();
            }
        }
        return documents;
    }






}

