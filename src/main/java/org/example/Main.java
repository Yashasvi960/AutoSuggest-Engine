package org.example;

import Trie.Trie;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Trie trie  = new Trie();
        trie.insert("java", 10);
        trie.insert("javascript", 15);
        trie.insert("javadoc", 5);
        trie.insert("json", 8);
        trie.insert("jam", 3);

        List<String> suggestions = trie.getSuggestionsWithFuzzyPrefix("Jsvs", 3);
        System.out.println(suggestions);
    }
}