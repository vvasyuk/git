package com.tryout.DailyCodingProblems.P22;

//Boggle is a game played on a 4 x 4 grid of letters. The goal is to find as many words as possible that can be formed by
//a sequence of adjacent letters in the grid, using each cell at most once. Given a game board and a dictionary of valid
//words, implement a Boggle solver.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class n227_boggle {
    static Character[][] grid= {
            {'a', 'b', 'c', 'd'},
            {'x', 'a', 'y', 'z'},
            {'t', 'z', 'r', 'r'},
            {'s', 'q', 'q', 'q'}
    };

    public static void main(String[] args) {
        ArrayList<String> dictionary = new ArrayList<>(Arrays.asList("bat", "car", "cat"));

        ArrayList<String> res = new ArrayList<>();

        Trie root = new Trie((char) 0);
        dictionary.forEach(w->constructTrie(w, root));

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                boolean [][] visited = new boolean [grid.length][grid.length];
                for (boolean[] row : visited) { Arrays.fill(row, false); };
                dfs(root, i, j,res,"");
            }
        }

        res.forEach(x-> System.out.println(x));
    }

    private static void dfs(Trie root, Integer x,Integer y,ArrayList<String> res, String accum) {
        Character c = grid[x][y];

        if(root.children.containsKey(c)){
            if (root.children.get(c).end){
                res.add(accum+c);
            }
            for( Coord coord : getNeighbors(x,y)){
                dfs(root.children.get(c), coord.x,coord.y,res, accum+c);
            }
        }
    }

    public static ArrayList<Coord> getNeighbors(Integer x, Integer y){
        ArrayList<Coord> res = new ArrayList<>();

        for (int i = x-1; i < x+2; i++) {
            for (int j = y-1; j < y+2; j++) {
                if (i > -1 && i < 4 && j > -1 && j < 4){
                    res.add(new Coord(i,j));
                }
            }
        }
        return res;
    }

    public static void constructTrie(String word, Trie root){
        Trie curr = root;
        for (Character c : word.toCharArray()){
            if (curr.children.containsKey(c)){
                curr=curr.children.get(c);
            }else{
                curr.children.put(c, new Trie(c));
                curr=curr.children.get(c);
            }
        }
        curr.end=true;
    }

    static class Trie{
        char value;
        HashMap<Character, Trie> children;
        boolean end;

        public Trie(char value) {
            this.value = value;
            this.children = new HashMap<>();
            this.end = false;
        }
    }

    private static class Coord {
        Integer x;
        Integer y;

        public Coord(Integer x, Integer y) {
            this.x = x;
            this.y = y;
        }
    }
}
