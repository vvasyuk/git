package com.tryout.generalPuzzles.a5stacksAndQueues;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

// Write a program which takes a pathname, and returns the shortest equivalent path¬
//name. Assume individual directories and files have names that use only alphanu¬
//meric characters. Subdirectory names may be combined using forward slashes (/),
//the current directory (.), and parent directory (..).
public class n9_4_normalize_pathnames {
    // It is natural to process the string from left-to-right, splitting on forward
    //slashes (/s). We record directory and file names. Each time we encounter a .., we
    //delete the most recent name, which corresponds to going up directory hierarchy.
    //Since names are processed in a last-in, first-out order, it is natural to store them in a
    //stack. Individual periods (.s) are skipped.
    //If the string begins with /, then we cannot go up from it. We record this in the
    //stack. If the stack does not begin with /, we may encounter an empty stack when
    //processing .., which indicates a path that begins with an ancestor of the current
    //working path. We need to record this in order to give the shortest equivalent path.
    //The final state of the stack directly corresponds to the shortest equivalent directory
    //path.
    //For example, if the string is sc//./, ./tc/awk/././, the stack progression is as
    //follows: (sc), (}, (tc), (tc, awk). Note that we skip three .s and the / after sc/.

    public static String shortestEquivalentPath(String path) {
        if (path.equals("")) {
            throw new IllegalArgumentException("Empty string is not a legal path.");
        }
        Deque<String> pathNames = new LinkedList<>();
        // Special case: starts with which is an absolute path.
        if (path.startsWith("/")) {
            pathNames.addFirst("/");
        }
        for (String token : path.split("/")) {
            if (token.equals("..")) {
                if (pathNames.isEmpty() || pathNames.peekFirst().equals("..")) {
                    pathNames.addFirst(token);
                } else {
                    if (pathNames.peekFirst().equals("/")) {
                        throw new IllegalArgumentException("Path error, trying to go up root " + path);
                    }
                    pathNames.removeFirst();
                }
            } else if (!token.equals(".") && !token.isEmpty()) { // Must be a name.
                pathNames.addFirst(token);
            }
        }
        StringBuilder result = new StringBuilder();
        if (!pathNames.isEmpty()) {
            Iterator<String> it = pathNames.descendingIterator();
            String prev = it.next();
            result.append(prev);
            while (it.hasNext()) {
                if (!prev.equals("/")) {
                    result.append("/");
                }
                prev = it.next();
                result.append(prev);
            }
        }
        return result.toString();
    }
}

