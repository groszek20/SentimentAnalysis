package com.mycompany.sentimentanalysis;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import static com.mycompany.sentimentanalysis.Constants.stopWordsList;

public class TextFilters {
    
    Lemmatization lematizeWord = new Lemmatization();

    public List<List> lineFilters(List<String> tweetList, String flag) {
        List<String> listSteam = new ArrayList<>();
        List<String> listNoSteam = new ArrayList<>();
        List<List> list= new ArrayList<>();
        StringTokenizer stringTokenizer;
        for (int i = 0; i < tweetList.size(); i++) {
            stringTokenizer = new StringTokenizer(tweetList.get(i));
            String singleWord = "";
            String line = "";
            String noSteamline = "";
            while (stringTokenizer.hasMoreElements()) {
                singleWord = stringTokenizer.nextElement().toString();
                noSteamline = noSteamline + " " + singleWord;
                singleWord = wordFiltering(singleWord);
                singleWord = lematizeWord.setWordStem(singleWord);
                line = line + " " + singleWord;
            }
            line = regexLine(line);
            line = removeStopWords(line);
            if (numberOfWords(line) > 5) {
                listSteam.add(line + " " + flag);
                listNoSteam.add(noSteamline);
            }
        }
        list.add(listSteam);
        list.add(listNoSteam);
        return list;
    }

    private static String regexLine(String line) {
        line = line.toLowerCase()
                .replaceAll("[^@a-ząćęłńóśźżA-Z\\s]", " ")
                .replaceAll("\\b\\w{1,2}\\b\\s?", " ")
                .replaceAll("(.)\\1{1,}", "$1")
                .replaceAll("\\s+", " ")
                .trim();
        return line;
    }

    private static String removeStopWords(String line) {
        for (String word : stopWordsList) {
            line = line.replaceAll("\\s" + word + "\\s", " ");
        }
        return line;
    }

    private static String wordFiltering(String singleWord) {
        if (singleWord.contains("https://") == true) {
            return "";
        } else if (singleWord.contains("@")) {
            return "@user";
        } else {
            return singleWord;
        }
    }

    private static int numberOfWords(String line) {
        Scanner scanner = new Scanner(line);
        int count = 0;
        while (scanner.hasNext()) {
            scanner.next();
            count++;
        }
        scanner.close();
        return count;
    }
}
