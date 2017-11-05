/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sentimentanalysis;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adam
 */
public class FileWrite {
    public void writeToTxt(List<String> list, String filename, int listSize){
        try {
            PrintWriter printWriter = new PrintWriter(filename,"UTF-8");
            String line = "";
            for (int i = 0; i < listSize; i++) {
                line = list.get(i);
                printWriter.print(line+"\r\n");
            }
            printWriter.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileWrite.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(FileWrite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
