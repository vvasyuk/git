package com.tryout.yamlMapper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DataSet {
    String type;
    LinkedHashMap<String, LinkedHashMap<String, List<String>>> data;

    public void setType(String type) {
        this.type = type;
    }

    public void setData(LinkedHashMap<String, LinkedHashMap<String, List<String>>> data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public LinkedHashMap<String, LinkedHashMap<String, List<String>>> getData() {
        return data;
    }
}
