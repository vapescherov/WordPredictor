package com.vp.wordpredictor.predictor;

import com.vp.wordpredictor.trie.Trie;
import com.vp.wordpredictor.utils.DictionaryEntry;
import com.vp.wordpredictor.utils.ReaderUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class PredictionDictionary implements WordPredictionService {

    private Trie trie;

    private Map<String, List<String>> cache;

    public PredictionDictionary(Trie trie) {
        this.trie = trie;
        this.cache = new HashMap<>();
    }

    public void load(BufferedReader reader) throws IOException {
        int size = ReaderUtils.getInt(reader);
        for (int i = 0; i < size; i++) {
            DictionaryEntry dictionaryEntry = ReaderUtils.getDictionaryEntry(reader);
            if (dictionaryEntry != null) {
                addWord(dictionaryEntry.getWord(), dictionaryEntry.getValue());
            }
        }
    }

    public void addWord(String word, int value) {
        trie.put(word, value);
    }

    public List<String> getWords(String prefix) {
        return cache.computeIfAbsent(prefix, this::findWordsByPrefix);
    }

    private List<String> findWordsByPrefix(String prefix) {
        Comparator<Entry<String, Integer>> reversed = (o1, o2) -> {
            int compareResult = o2.getValue().compareTo(o1.getValue());
            if (compareResult == 0) {
                return o1.getKey().compareTo(o2.getKey());
            }
            return compareResult;
        };

        return trie.prefixMap(prefix).entrySet().stream()
                .sorted(reversed)
                .limit(10)
                .map(Entry::getKey)
                .collect(Collectors.toList());
    }

}
