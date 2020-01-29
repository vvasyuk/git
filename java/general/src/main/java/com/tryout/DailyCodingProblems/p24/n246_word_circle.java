package com.tryout.DailyCodingProblems.p24;


import java.util.*;

// Given a list of words, determine whether the words can be chained to form a circle.
// A word X can be placed in front of another word Y in a circle if the last character of X is same as the first character of Y.
//
//For example, the words ['chair', 'height', 'racket', touch', 'tunic'] can form the following circle: chair --> racket --> touch --> height --> tunic --> chair.
public class n246_word_circle {
    public static void main(String[] args) {
        String[] input = {"chair", "height", "racket", "touch", "tunic"};

        HashMap<Character, List<Character>> g = makeGraph(input);
        boolean areDegreesEqual = areDegreesEqual(g);
        System.out.println("isConnected: " + isConnected(g));
    }

    private static boolean isConnected(HashMap<Character, List<Character>> g) {
        Character start = new ArrayList<>(g.keySet()).get(0);

        System.out.println("graph: ");
        g.forEach((k,v)-> System.out.println(k + ":" + v));
        Set<Character> component = findComponent(g, new HashSet<Character>(), start);

        HashMap<Character, List<Character>> reversedG = new HashMap<>();
        g.forEach((k,l)-> {
            l.forEach(v->{
                if (reversedG.containsKey(v)){
                    reversedG.get(v).add(k);
                }else{
                    reversedG.put(v, new ArrayList<Character>(Arrays.asList(k)));
                }
            });
        });
        System.out.println("reversed graph: ");
        reversedG.forEach((k,v)-> System.out.println(k + ":" + v));

        Set<Character> reversedComponent = findComponent(reversedG, new HashSet<Character>(), start);
        return reversedComponent.equals(component) && component.equals(g.keySet());
    }

    private static HashSet<Character> findComponent(HashMap<Character, List<Character>> g, HashSet<Character> visited, Character currentWord) {
        System.out.println("adding: " + currentWord);
        visited.add(currentWord);

        for (Character neighbor : g.get(currentWord)){
            if (!visited.contains(neighbor)) findComponent(g, visited, neighbor);
        }
        return visited;
    }

    private static boolean areDegreesEqual(HashMap<Character, List<Character>> g) {
        HashMap<Character, Integer> inMap = new HashMap<>();
        HashMap<Character, Integer> outMap = new HashMap<>();

        g.forEach((k,l)-> {
            l.forEach(v->{
                inMap.put(k, inMap.getOrDefault(k,0)+1);
                outMap.put(v, outMap.getOrDefault(v,0)+1);
            });
        });
//        System.out.println("inMap");
//        inMap.forEach((k,v)-> System.out.println(k + ":" + v));
//        System.out.println("outMap");
//        outMap.forEach((k,v)-> System.out.println(k + ":" + v));
        return inMap.equals(outMap);
    }

    private static HashMap<Character, List<Character>> makeGraph(String[] input) {
        HashMap<Character, List<Character>> g = new HashMap<>();

        for (int i = 0; i < input.length; i++) {
            if (g.containsKey(input[i].charAt(0))){
                g.get(input[i].charAt(0)).add(input[i].charAt(input[i].length()-1));
            }else{
                g.put(input[i].charAt(0), new ArrayList<Character>(Arrays.asList(input[i].charAt(input[i].length()-1))));
            }
        }
        return g;
    }
}
