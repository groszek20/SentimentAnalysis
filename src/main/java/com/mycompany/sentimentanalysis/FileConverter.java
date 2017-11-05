package com.mycompany.sentimentanalysis;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class FileConverter {
    
    public List<String> convertTxtToList(String filePath) throws IOException {
        File file = new File(filePath);
        List<String> tweetList = new ArrayList<>();
        LineIterator it = FileUtils.lineIterator(file, "UTF-8");
        try {
            while (it.hasNext()) {
                String line = it.nextLine();
                tweetList.add(line);
            }
        } finally {
            LineIterator.closeQuietly(it);
        }
        return tweetList;
    }

    public List<String> convertXmlToList(String filePath) throws ParserConfigurationException, IOException, SAXException {
        List<String> tweetList = new ArrayList<>();
        File file = new File(filePath);
        String tweet;
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("arr");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    tweet = eElement.getElementsByTagName("str").item(0).getTextContent();
                    tweetList.add(tweet);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tweetList;
    }

}
