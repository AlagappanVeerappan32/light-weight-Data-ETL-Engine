package dal.assignment2.extraction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

public class ExtractionEngine implements Extract {

    private static final Logger LOGGER = Logger.getLogger(ExtractionEngine.class.getName());


    /***
     * This method is used for fetching the data from NewsAPI, first we create a connection,
     * once the connection is stable, we fetch the data and return it to process further.
     * "GET" is used to get the data
     * @param url url is passed as a parameters with list of given keywords
     * @return it returns the response data from the API
     * @throws IOException throws IOException has the connection may or may not exist.
     */
    @Override
    public String extractingData(String url) throws IOException{
        int responseCode;
        InputStream inputStream;
        String response = null;
        URL getUrl = new URL(url);
        HttpURLConnection httpURLConnection = (HttpURLConnection) getUrl.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.connect();
        responseCode=httpURLConnection.getResponseCode();
        if(responseCode == HttpURLConnection.HTTP_OK)
        {
            inputStream=httpURLConnection.getInputStream();
            if(inputStream!=null) {
                response = readingResponse(inputStream);
                LOGGER.info(response);
            }else
            {
                LOGGER.warning("The following API request response is empty");
            }
        }else {
            LOGGER.info("The error is "+ responseCode);
        }
        httpURLConnection.disconnect();
        return response;
    }

    /***
     * This method is used within this class , just to read the response and convert the response to string.
     * @param responseData passing the raw response
     * @return the response data in string
     * @throws IOException as the data may or may not exist
     */

    private String readingResponse(InputStream responseData) throws IOException {
        StringBuilder stringBuilder=new StringBuilder();
        try(BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(responseData)))
        {
            String lineToRead;
            while((lineToRead=bufferedReader.readLine())!=null)
            {
                stringBuilder.append(lineToRead);
            }
        }
        return stringBuilder.toString();
    }
}
