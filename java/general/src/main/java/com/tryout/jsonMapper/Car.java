package com.tryout.jsonMapper;

import java.util.List;
import java.util.Map;

public class Car {
    private String color;
    private String type;
    private List<String> feature;
    private Map<String, String> nested;
    private Map<String, Map<String, String>> nested2;

    public Car(String color, String type) {
        this.color = color;
        this.type = type;
    }

    public void setNested2(Map<String, Map<String, String>> nested2) {
        this.nested2 = nested2;
    }

    public Map<String, Map<String, String>> getNested2() {
        return nested2;
    }

    public void setNested(Map<String, String> nested) {
        this.nested = nested;
    }

    public Map<String, String> getNested() {
        return nested;
    }

    public List<String> getFeature() {
        return feature;
    }

    public void setFeature(List<String> feature) {
        this.feature = feature;
    }

    public Car() {
    }

    public String getColor() {
        return color;
    }

    public String getType() {
        return type;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setType(String type) {
        this.type = type;
    }
}
