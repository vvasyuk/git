package com.tryout.DailyCodingProblems.P22;

//Given an absolute pathname that may have . or .. as part of it, return the shortest standardized path.
//For example, given "/usr/bin/../bin/./scripts/../", return "/usr/bin/".

public class n222_path_stack {

    public static void main(String[] args) {
        String path = "/usr/bin/../bin/./scripts/../";
        System.out.println(path);
    }
}
