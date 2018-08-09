package com.example.demo.service;

import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
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

    public void getInputStream(Exchange exchange) {
        InputStream inputStream = new ByteArrayInputStream("test string, test huistring".getBytes());
        exchange.getIn().setBody(inputStream);
    }
}
