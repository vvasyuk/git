package com.tryout.jsonMapper;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JsonMapper {

    public void execute() throws IOException {
        System.out.println("starting json mapper");
        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        Car car = objectMapper.readValue(new File("D:\\work\\tryout\\java\\general\\src\\main\\resources\\car.json"), Car.class);
//
//        System.out.println(car.getType());
//        System.out.println(car.getFeature());
//        System.out.println(car.getNested());
//        System.out.println(car.getNested2());
//
        FooBar fooBar = objectMapper.readValue(new File("D:\\work\\tryout\\java\\general\\src\\main\\resources\\foobar.json"), FooBar.class);
        System.out.println(fooBar.getBar());
        System.out.println(fooBar.getFoo());

    }
}

