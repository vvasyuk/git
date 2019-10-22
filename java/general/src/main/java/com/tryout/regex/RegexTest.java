package com.tryout.regex;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {
// ^cat      matches if you have the beginning of a line, followed immediately by cat
// cat$      finds c·a·t only at the end of the line
// [ea]      matches either e or a
// gr[ea]y   grey or gray
// [0-9]     digits
// [a-z]     lowercase letters
// [^1-6]    matches a character that’s not 1 through 6
// 03.19.76  . matches any char 03/19/76 or 03-19-76
// gr(a|e)y  grey or gray
// colou?r   color or colour ? means optional
// +         means one or more of the immediately-preceding item
// *         means any number, including none, of the item.
// [a-zA-Z]{1,5}  matches from one to five letters
// (?=\d)    lookahead  successful at positions where a digit comes next (matches POSITION)
// (?<=\d)   lookbehind looks back successful at positions with a digit to the left

    public static void runRegexReplaceCommaInsideQuotes(){
        String s = "abc,def,\"h,i,g\",xyz";

        System.out.println(s.replaceAll(",(?=[^\"]*\"[^\"]*(?:\"[^\"]*\"[^\"]*)*$)", ""));
    }


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
