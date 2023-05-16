package dal.assignment2.dataprocessing;

import java.util.List;
import java.util.Map;

public interface Processing {
    List<Map<String, String>> getTitleAndContent(String response);
}
