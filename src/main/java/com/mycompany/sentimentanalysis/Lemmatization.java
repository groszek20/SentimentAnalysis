package com.mycompany.sentimentanalysis;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import morfologik.stemming.WordData;
import morfologik.stemming.polish.PolishStemmer;

/**
 *
 * @author Adam
 */
public class Lemmatization {

    public String setWordStem(String word) {

        PolishStemmer stemmer = new PolishStemmer();
        ArrayList<CharSequence> stemList = new ArrayList<CharSequence>();
        List<WordData> processing = stemmer.lookup(word);
        processing.stream().forEach((wordData) -> {
            stemList.add(wordData.getStem());
        });
        if (stemList.size() > 0) {
            return stemList.get(0).toString();
        } else if (word.equals("@user")) {
            return word;
        } else {
            return "";
        }
    }
}
