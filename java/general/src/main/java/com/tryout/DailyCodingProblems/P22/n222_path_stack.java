package com.tryout.DailyCodingProblems.P22;

//Given an absolute pathname that may have . or .. as part of it, return the shortest standardized path.
//For example, given "/usr/bin/../bin/./scripts/../", return "/usr/bin/".

import java.util.Arrays;
import java.util.LinkedList;

public class n222_path_stack {

    public static void main(String[] args) {
        String path = "/usr/bin/../bin/./scripts/../";  // /usr/bin/
        LinkedList<String> res = new LinkedList<String>();
        System.out.println(path);

        String[] splitted = path.split("/");

        Arrays.stream(splitted).forEach(x-> {
            if(x.equals("..")){
                res.removeLast();
            }else if (!x.equals(".")){
                res.addLast(x);
            }
        });
        res.forEach(x-> System.out.print(x));
    }
}
