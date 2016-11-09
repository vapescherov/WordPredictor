package com.vp.wordpredictor.predictor;

import java.util.List;

public interface WordPredictionService {
    List<String> getWords(String prefix);
}
