package com.vp.wordpredictor.trie;

import java.util.Map;

public interface Trie {
	Map<String, Integer> prefixMap(String prefix);

	void put(String word, Integer value);
}
