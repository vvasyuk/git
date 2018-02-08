package com.example.demo.service;

import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * Created by Jopa on 10/31/2017.
 */
@Component("SomeService")
public class SomeService {
    public void doOPrint(){
        System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
    }

    public void printXml(String msg){
        System.out.println("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ");
        HashMap<String, String> namespaceMap, whatToTakeFromXmlMap, resultMap;

        //resultMap=CustomXmlParser.getMapFromXml(msg, whatToTakeFromXmlMap, namespaceMap);
        System.out.println("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ");
    }
}
