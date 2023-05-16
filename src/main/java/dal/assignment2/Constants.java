package dal.assignment2;

public final class Constants {
    public static final String X_API_KEY = "a75a0e79c4014c1fac14209fa5c67be6";
    public static final String CONNECTION_STRING = "mongodb+srv://alex:alexedge@assignment2.65nu9dc.mongodb.net/?retryWrites=true&w=majority";
    public static final String DATABASE_NAME = "myMongoNews";
    public static final String TOPIC_NAME = "<topic>";
    public static final String DIRECTORY = "src/main/resources/<topic>";
    public static final String FILE_NAME = "src/main/resources/<topic>/file";
    public static final String URL = "https://newsapi.org/v2/top-headlines?q=<topic>&apiKey=" + Constants.X_API_KEY;
}
