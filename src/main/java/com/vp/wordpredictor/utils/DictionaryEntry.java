package com.vp.wordpredictor.utils;

public class DictionaryEntry {
	private String word;
	private int value;

	public DictionaryEntry(String word, int value) {
		this.word = word;
		this.value = value;
	}

	public String getWord() {
		return word;
	}

	public int getValue() {
		return value;
	}

}