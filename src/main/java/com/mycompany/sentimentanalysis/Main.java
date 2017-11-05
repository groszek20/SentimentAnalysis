
package com.mycompany.sentimentanalysis;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class Main {
    
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        App app  = new App();
        app.run();
    }
    
}
