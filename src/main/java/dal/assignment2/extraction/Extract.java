package dal.assignment2.extraction;

import java.io.IOException;
import java.io.InputStream;

public interface Extract {
    String extractingData(String url) throws IOException;
}
