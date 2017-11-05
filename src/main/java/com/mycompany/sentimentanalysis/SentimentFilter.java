package com.mycompany.sentimentanalysis;

import java.util.ArrayList;
import java.util.List;

public class SentimentFilter {
    
    public List<String> emoticonsFilter (List<String> list, String emoticon){
    List<String> onlyOneTypeList = new ArrayList<>();
    String line;
        for (int i = 0; i < list.size(); i++) {
            line = list.get(i);
            if(line.contains(emoticon))onlyOneTypeList.add(line);
        }
        return onlyOneTypeList;
    }
}
