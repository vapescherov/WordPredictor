package com.vp.wordpredictor;

import com.vp.wordpredictor.predictor.PredictionDictionary;
import com.vp.wordpredictor.trie.MyTrie;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;

public class App {
    public static void main(String[] args) {
        PredictionDictionary dict = new PredictionDictionary(new MyTrie());
        Client client = new Client(dict);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            dict.load(reader);
            client.load(reader);
            client.write(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
