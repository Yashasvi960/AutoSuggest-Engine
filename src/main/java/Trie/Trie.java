package Trie;

import java.util.*;

public class Trie {
    public  TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word, int freq) {
        TrieNode node = root;
        for(char c: word.toCharArray()) {
            if(!node.children.containsKey(c)) {
                node.children.put(c, new TrieNode());
            }
            node = node.children.get(c);
        }
        node.isEndOfWord = true;
        node.wordFrequency += freq;
    }

    public List<String> getSuggestionsWithFuzzyPrefix(String input, int k) {
        TrieNode node = root;
        PriorityQueue<String[]> pq = new PriorityQueue<>((a,b) -> {
            int freqMap = Integer.compare(Integer.parseInt(a[1]), Integer.parseInt(b[1]));
            return freqMap!=0?freqMap:b[0].compareTo(a[0]);
        });
       Set<String> seen = new HashSet<>();
        fuzzyPrefixSearch(node, input.toLowerCase(), "", k, pq, 2, seen);
        List<String> result = new ArrayList<>();
        while(!pq.isEmpty()) {
            result.add(0, pq.poll()[0]);
        }

        return result;
    }

    public void fuzzyPrefixSearch(TrieNode node, String input, String currPrefix,
                                  int k, PriorityQueue<String[]> pq,
                                  int maxDistance, Set<String> seen) {
        if(editDistance(input, currPrefix)<=maxDistance) {
          collectWords(node, currPrefix, k, pq, seen);
        }

        for(Map.Entry<Character, TrieNode> entry: node.children.entrySet()) {
            Character entryKey = entry.getKey();
            TrieNode child = entry.getValue();
            fuzzyPrefixSearch(child, input, currPrefix+entryKey, k, pq, maxDistance, seen);
        }
    }

    public int editDistance(String input1, String input2) {
        int m1 = input1.length();
        int m2 = input2.length();

        int dp[][] = new int[m1+1][m2+1];
        for(int i = 0; i<m1+1; i++) {
            dp[i][0] = i;
        }
        for(int j = 0;j<m2+1; j++) {
            dp[0][j] = j;
        }

        for(int i =1 ; i<m1+1; i++) {
            for(int j =1; j<m2+1; j++) {
                if(input1.charAt(i-1)==input2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else{
                    dp[i][j] = 1+Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1]));
                }
            }
        }

        return dp[m1][m2];
    }

    public void collectWords(TrieNode node, String currPrefix,
                                  int k, PriorityQueue<String[]> pq, Set<String> seen){
      if(node.isEndOfWord && !seen.contains(currPrefix)) {
          pq.offer(new String[] {currPrefix, String.valueOf(node.wordFrequency)});
          seen.add(currPrefix);
          if(pq.size()>k) pq.poll();
      }

        for(Map.Entry<Character, TrieNode> entry: node.children.entrySet()) {
            Character entryKey = entry.getKey();
            TrieNode child = entry.getValue();
            collectWords(child, currPrefix+entryKey, k, pq, seen);
        }
    }
}
