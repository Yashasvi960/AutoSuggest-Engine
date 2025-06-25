package Trie;

public class Trie {
    public  TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word, int freq) {
        TrieNode node = root;
        for(char c: word.toCharArray()) {
            if(node.children[c-'a']==null) {
                node.children[c-'a']=new TrieNode();
            }
            node = node.children[c-'a'];
        }
        node.isEndOfWord = true;
        node.wordFrequency += freq;
    }
}
