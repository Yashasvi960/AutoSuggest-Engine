package Trie;

public class TrieNode {
    public TrieNode[] children;
    public boolean isEndOfWord;

    public int wordFrequency;

    public TrieNode() {
        children = new TrieNode[26];
        isEndOfWord = false;
        wordFrequency = 0;
    }
}
