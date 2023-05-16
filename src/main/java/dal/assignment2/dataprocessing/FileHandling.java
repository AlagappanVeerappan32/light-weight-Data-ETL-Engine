package dal.assignment2.dataprocessing;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FileHandling implements FileOperations {


    /***
     * this method is used to create a directory if not present.
     * @param directoryName name of the directory which will be accordance to the keyword
     * @throws IOException throws IOException as the file may or may not exist
     */
    @Override
    public void createDirectory(String directoryName) {

        File directory = new File(directoryName);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }


    /***
     * This method is used to create only 5 title and content per file and repeat the process for total number of values
     * in th list of hash map.
     * @param filteredData this the list of hash map with cleaned data from the response with title and content
     * @param filename file name where the data will be stored
     * @param directoryName and directory name based on keyword
     * @throws IOException throws IOException as the file may or may not exist
     */
    @Override
    public void writeFile(List<Map<String, String>> filteredData, String filename, String directoryName) throws IOException {

        createDirectory(directoryName);
        int fileNumber = 1;
        int fileSize = 5;
        Iterator<Map<String, String>> iterator = filteredData.listIterator();
        while (iterator.hasNext()) {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename + fileNumber + ".txt"));
            int count = 0;
            while (iterator.hasNext() && count < fileSize) {
                Map<String, String> contents = iterator.next();
                String title = contents.get("title");
                String content = contents.get("content");
                String values = "title=" + title + "\n" + "content=" + content;
                bufferedWriter.write(values);
                bufferedWriter.newLine();
                count++;
            }
            bufferedWriter.close();
            fileNumber++;
        }
    }
}


