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

        input = input.replaceAll("(sql:)", "");
        input = myReplace(input, ":#(\\w+)?",
                (s)->{return (String) replaceMap.get(s);});

        System.out.println(input);
    }

    private static String myReplace(String input, String pattern, Function<String, String> f){
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(input);

        boolean res = m.find();
        if(res){
            StringBuffer sb = new StringBuffer();
            do{
                m.appendReplacement(sb, f.apply(m.group(1)));
                res = m.find();
            } while (res);
            m.appendTail(sb);
            return sb.toString();
        }
        return input;
    }
}
