package com.tryout.regex;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {

    public static void runRegexTest(){
        Map<String, String> replaceMap = new HashMap<>();
        replaceMap.put("value1", "10");
        replaceMap.put("value2", "20");
        replaceMap.put("value3", "30");

        String input = "sql:call my_pack.set_v1(:#value1),sql:call my_pack.set_v1(:#value2) from dual,sql:call my_pack.set_v1(:#value3) from dual";

        System.out.println(
                replace(input, ":#(\\w+)'?",
                        (s)->{return (String) replaceMap.get(s);})
        );

        System.out.println(
                replace(input, "(sql:)'?",
                        (s)->{return "";})
        );


    }

    private static String replace(String input, String pattern, Function<String, String> f){
        StringBuffer newString = new StringBuffer();
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(input);

        while (m.find()) {
            String id= m.group(1);
            String newId = f.apply(id);
            m.appendReplacement(newString, newId);
        }
        m.appendTail(newString);

        return newString.toString();
    }
}
