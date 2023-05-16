package dal.assignment2.dataprocessing;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface FileOperations {
    void createDirectory(String directoryName);
    void writeFile(List<Map<String, String>> filteredData, String filename, String directoryName) throws IOException;
}
