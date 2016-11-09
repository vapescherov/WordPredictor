package com.vp.wordpredictor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.vp.wordpredictor.predictor.PredictionDictionary;
import com.vp.wordpredictor.trie.MyTrie;

public class PredictionDictionaryTest {

	private PredictionDictionary dict;

	@Before
	public void setUp() {
		dict = new PredictionDictionary(new MyTrie());
	}

	@Test
	public void testValuesOrder() {
		dict.addWord("aaa", 3);
		dict.addWord("aaaa", 5);
		dict.addWord("aa", 6);
		dict.addWord("aaaaa", 1);

		List<String> actualResult = dict.getWords("a");
		List<String> expectedResult = new ArrayList<>(Arrays.asList("aa", "aaaa", "aaa", "aaaaa"));

		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void wordsWithSameValueOrder() {
		dict.addWord("abc", 10);
		dict.addWord("acb", 10);
		dict.addWord("aab", 10);

		List<String> actualResult = dict.getWords("a");
		List<String> expectedResult = new ArrayList<>(Arrays.asList("aab", "abc", "acb"));

		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void wordNotInDict_returnsEmptyList() {
		dict.addWord("bb", 5);

		List<String> actualResult = dict.getWords("a");
		List<String> expectedResult = Collections.emptyList();
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void emptyDict_returnsEmptyList() {
		List<String> actualResult = dict.getWords("a");
		List<String> expectedResult = Collections.emptyList();

		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void getEmptyWord_returnsEmptyList() {
		List<String> actualResult = dict.getWords("");
		List<String> expectedResult = Collections.emptyList();

		assertEquals(expectedResult, actualResult);
	}
}
