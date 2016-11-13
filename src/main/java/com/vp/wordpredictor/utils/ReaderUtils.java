package com.vp.wordpredictor.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Logger;

public class ReaderUtils {

    private static Logger log = Logger.getLogger(ReaderUtils.class.getName());

    public static int getInt(BufferedReader reader) throws IOException {
        int value = 0;
        try {
            value = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            log.severe("Unable to parse input to int");
        }
        return value;
    }

    public static DictionaryEntry getDictionaryEntry(BufferedReader reader) throws IOException {
        String[] entry = reader.readLine().split(" ");
        if (entry.length != 2) {
            log.severe("Unable to parse input to dictionary entry");
            return null;
        }
        String word = entry[0];
        int value = 0;
        try {
            value = Integer.parseInt(entry[1]);
        } catch (NumberFormatException e) {
            log.severe("Unable to parse input to dictionary value");
        }
        return new DictionaryEntry(word, value);
    }
}
