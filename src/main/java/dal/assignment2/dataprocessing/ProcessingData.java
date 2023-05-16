package dal.assignment2.dataprocessing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ProcessingData implements Processing{


    /***
     * In this method, at first we are creating regex for title and content and also the special characters to be
     * eliminated. we are using pattern matcher to find title and content, if title is present , we are checking for
     * content, if both are present we are removing the special characters from the response, and storing it in a list
     * of hash map.
     * @param response passing the response
     * @return list of hash map with title and content
     */
    @Override
    public List<Map<String, String>> getTitleAndContent(String response) {

        List<Map<String, String>> articles = new ArrayList<>();

        String regexTitle = "\"title\":\"([^\"]+)\"";
        String regexContent = "\"content\":\"([^\"]+)\"" + "|" + "\"content\":\\s*null";
        String regexToRemove =  "</?li>|<ul><li>|\\.\\.\\. - |\\s*\\[\\+\\d+\\s*\\w+\\]|\\\\|\\/ | \\\n|\\|";


        Pattern patternTitle = Pattern.compile(regexTitle);
        Pattern patternContent = Pattern.compile(regexContent);

        Matcher matcherTitle = patternTitle.matcher(response);
        Matcher matcherContent = patternContent.matcher(response);


        while (matcherTitle.find()) {
            Map<String, String> storingTitleAndContent = new HashMap<>();
            String title = matcherTitle.group(1);


            if (matcherContent.find() || matcherContent==null) {

                String content = matcherContent.group(1);

                if(content!=null) {
                    content = content.replaceAll(regexToRemove, "");
                }
                storingTitleAndContent.put("title", title);
                storingTitleAndContent.put("content", content);
                articles.add(storingTitleAndContent);
            }
        }
        return articles;
    }
}

