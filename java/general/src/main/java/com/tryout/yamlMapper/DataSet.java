package com.tryout.yamlMapper;

import java.util.List;
import java.util.Map;

public class DataSet {
    String type;
    Map<String, Map<String, List<Integer>>> data;

    public void setType(String type) {
        this.type = type;
    }

    public void setData(Map<String, Map<String, List<Integer>>> data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public Map<String, Map<String, List<Integer>>> getData() {
        return data;
    }
}
