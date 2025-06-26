package Trie;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    public Map<Character, TrieNode> children;
    public boolean isEndOfWord;

    public int wordFrequency;

    public TrieNode() {
        children = new HashMap();
        isEndOfWord = false;
        wordFrequency = 0;
    }
}
