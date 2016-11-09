package com.vp.wordpredictor.trie;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MyTrie implements Trie {

	private TrieNode root;

	public MyTrie() {
		this.root = new TrieNode();
	}

	@Override
	public Map<String, Integer> prefixMap(String prefix) {
		TrieNode node = getPrefixNode(prefix);
		Map<String, Integer> words = new HashMap<>();
		if (node != null) {
			words.putAll(node.getUnderlayingWords(prefix));
			if (node.hasValue()) {
				words.put(prefix, node.getValue());
			}
		}
		return words;
	}

	private TrieNode getPrefixNode(String prefix) {
		TrieNode currentNode = root;
		for (int i = 0; i < prefix.length(); i++) {
			char letter = prefix.charAt(i);
			currentNode = currentNode.getChildren().get(letter);
			if (currentNode == null) {
				return null;
			}
		}
		return currentNode;
	}

	@Override
	public void put(String word, Integer value) {
		TrieNode node = root;
		for (int i = 0; i < word.length(); i++) {
			node = node.getOrCreateChild(word.charAt(i));
		}
		node.setValue(value);
	}

	private class TrieNode {

		private Integer value;

		private Map<Character, TrieNode> children;

		private TrieNode() {
			this.children = new HashMap<>();
		}

		private Integer getValue() {
			return value;
		}

		private void setValue(Integer value) {
			this.value = value;
		}

		private Map<Character, TrieNode> getChildren() {
			return children;
		}

		private TrieNode getOrCreateChild(char letter) {
			return getChildren().computeIfAbsent(letter, key -> new TrieNode());
		}

		private Map<StringBuilder, Integer> getReverseUnderlayingChars() {
			Map<StringBuilder, Integer> wordBuilders = new HashMap<>();
			for (Entry<Character, TrieNode> child : children.entrySet()) {
				TrieNode childNode = child.getValue();
				if (childNode.hasValue()) {
					wordBuilders.put(new StringBuilder().append(child.getKey()), childNode.getValue());
				}

				Map<StringBuilder, Integer> reverseUnderlayingChars = childNode.getReverseUnderlayingChars();
				for (Entry<StringBuilder, Integer> wordBuilderEntry : reverseUnderlayingChars.entrySet()) {
					wordBuilders.put(wordBuilderEntry.getKey().append(child.getKey()), wordBuilderEntry.getValue());
				}
			}
			return wordBuilders;
		}

		private Map<String, Integer> getUnderlayingWords(String prefix) {
			Map<String, Integer> result = new HashMap<>();
			for (Entry<StringBuilder, Integer> reverseCharsEntry : getReverseUnderlayingChars().entrySet()) {
				String word = reverseAndInsertPrefix(prefix, reverseCharsEntry.getKey());
				result.put(word, reverseCharsEntry.getValue());
			}
			return result;
		}

		private String reverseAndInsertPrefix(String prefix, StringBuilder reverseChars) {
			return reverseChars.reverse().insert(0, prefix).toString();
		}

		public boolean hasValue() {
			return value != null;
		}

	}
}
