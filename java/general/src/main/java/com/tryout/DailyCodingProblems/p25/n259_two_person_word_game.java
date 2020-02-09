package com.tryout.DailyCodingProblems.p25;

import java.util.Arrays;
import java.util.HashMap;

public class n259_two_person_word_game {
    public static void main(String[] args) {
        String[] dict = new String[]{"cat", "calf", "dog", "bear"};

        Trie root = new Trie();
        Arrays.stream(dict).forEach(x->add(x,root));

        for (Character k : root.children.keySet()){
            System.out.println(k);
        }
    }

    public static Trie get(Trie root, String prefix){
        Trie temp = root;
        for (Character c : prefix.toCharArray()){
            if (temp.children.containsKey(c)){
                temp = temp.children.get(c);
            }else{
                return null;
            }
        }
        return temp;
    }
    
    public static void add(String word, Trie root){
        Trie curr = root;
        for (Character c : word.toCharArray()){
            if (!curr.children.containsKey(c)){
                curr.children.put(c, new Trie());
            }
            curr=curr.children.get(c);
        }
        curr.end=true;
    }

    static class Trie{
        HashMap<Character, Trie> children;
        boolean end;

        public Trie() {
            this.children = new HashMap<>();
            this.end = false;
        }
    }
}

// def is_winning(trie, prefix):
//    root = trie.get(prefix)
//
//    if '#' in root:
//        return False
//    else:
//        next_moves = [prefix + letter for letter in root]
//        if any(is_winning(trie, move) for move in next_moves):
//            return False
//        else:
//            return True