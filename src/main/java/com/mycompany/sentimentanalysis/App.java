package com.mycompany.sentimentanalysis;

import com.mycompany.sentimentanalysis.FileConverter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class App {

    public void run() throws IOException, SAXException, ParserConfigurationException {
        FileConverter fc = new FileConverter();
        List<String> onlyHappyTweetList = new ArrayList<>();
        List<String> onlySadTweetList = new ArrayList<>();
        List<String> tweetList = fc.convertTxtToList("files/input/happy/select");
        System.out.println("wielkość listy : " + tweetList.size());
        
        SentimentFilter sentimentFilter = new SentimentFilter();
        onlyHappyTweetList = sentimentFilter.emoticonsFilter(tweetList, ":)");
        onlySadTweetList = sentimentFilter.emoticonsFilter(tweetList, ":(");
        System.out.println("tylko tweety z ':)' : " + onlyHappyTweetList.size());
        System.out.println("tylko tweety z ':(' : " + onlySadTweetList.size());
        
        List<List> onlyHappyTweetListOfArrays = new ArrayList<>();
        List<List> onlySadTweetListOfArrays = new ArrayList<>();
        
        TextFilters tf = new TextFilters();
        onlyHappyTweetListOfArrays = tf.lineFilters(onlyHappyTweetList, ";1");
        onlySadTweetListOfArrays =tf.lineFilters(onlySadTweetList, ";0");
        System.out.println("lista ':)' po filtrowaniu: " + onlyHappyTweetListOfArrays.get(0).size());
        System.out.println("lista ':(' po filtrowaniu: " + onlySadTweetListOfArrays.get(0).size());
        System.out.println("lista Arrayów ':)' po filtrowaniu: " + onlyHappyTweetListOfArrays.size());
        System.out.println("lista Arrayów ':(' po filtrowaniu: " + onlySadTweetListOfArrays.size());
        System.out.println("lista bez filtra ':)' po filtrowaniu: " + onlyHappyTweetListOfArrays.get(1).size());
        System.out.println("lista bez filtra ':(' po filtrowaniu: " + onlySadTweetListOfArrays.get(1).size());
        
        List<String> onlyHappyTweetListFiltered = onlyHappyTweetListOfArrays.get(0);
        List<String> onlyHappyTweetListNoFiltered = onlyHappyTweetListOfArrays.get(1);
        
        List<String> onlySadTweetListFiltered = onlySadTweetListOfArrays.get(0);    
        List<String> onlySadTweetListNoFiltered = onlySadTweetListOfArrays.get(1); 
        
        FileWrite fileWrite = new FileWrite();
        fileWrite.writeToTxt(onlyHappyTweetListFiltered, "happyFiltered", 10000);
        fileWrite.writeToTxt(onlyHappyTweetListNoFiltered, "happyNoFiltered", 10000);
        fileWrite.writeToTxt(onlySadTweetListFiltered, "sadFiltered", 10000);
        fileWrite.writeToTxt(onlySadTweetListNoFiltered, "sadNoFiltered", 10000);
    }

}
