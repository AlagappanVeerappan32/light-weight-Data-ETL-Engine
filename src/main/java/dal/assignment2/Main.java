package dal.assignment2;

import java.io.IOException;


public class Main {

    /***
     * We are passing the given keywords as a list of strings and
     * inorder to execute parallel we are starting the thread.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        for(String keyword: new String []{"Canada", "University", "Dalhousie", "Halifax", "Canada Education",
                "Moncton", "hockey", "Fredericton", "celebration"}) {
            new Thread(new Runner(keyword)).start();
        }
    }
}


