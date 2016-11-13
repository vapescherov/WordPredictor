package com.vp.wordpredictor;

import com.vp.wordpredictor.predictor.WordPredictionService;
import com.vp.wordpredictor.utils.ReaderUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Client {

    private List<String> prefixes;

    private WordPredictionService service;

    public Client(WordPredictionService service) {
        this.service = service;
        this.prefixes = new LinkedList<>();
    }

    public void load(BufferedReader reader) throws IOException {
        int size = ReaderUtils.getInt(reader);
        for (int i = 0; i < size; i++) {
            prefixes.add(reader.readLine());
        }
    }

    public void write(BufferedWriter writer) throws IOException {
        for (String prefix : prefixes) {
            for (String word : service.getWords(prefix)) {
                writer.write(word);
                writer.newLine();
            }
            writer.newLine();
            writer.flush();
        }
    }

}
