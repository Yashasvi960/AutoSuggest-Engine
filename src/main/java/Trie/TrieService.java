package Trie;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrieService {

    private Trie trie;

    public TrieService() {
        this.trie = new Trie();
        trie.insert("java", 10);
        trie.insert("javascript", 15);
        trie.insert("javadoc", 5);
        trie.insert("json", 8);
        trie.insert("jam", 3);
    }

    public List<String> getSuggestions(String query, int frequency) {
        return trie.getSuggestionsWithFuzzyPrefix(query, frequency);
    }
}
