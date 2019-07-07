package com.tryout.yamlMapper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MergedDataSet {
    String header;
    String mappedCfgBlockName;
    String type;
    Integer cols;
    LinkedHashMap<String, String> properties;
    Map<String, List<Integer>> rows;

    public void setHeader(String header) {
        this.header = header;
    }

    public void setMappedCfgBlockName(String mappedCfgBlockName) {
        this.mappedCfgBlockName = mappedCfgBlockName;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCols(Integer cols) {
        this.cols = cols;
    }

    public void setProperties(LinkedHashMap<String, String> properties) {
        this.properties = properties;
    }

    public void setRows(Map<String, List<Integer>> rows) {
        this.rows = rows;
    }

    public String getHeader() {
        return header;
    }

    public String getMappedCfgBlockName() {
        return mappedCfgBlockName;
    }

    public String getType() {
        return type;
    }

    public Integer getCols() {
        return cols;
    }

    public LinkedHashMap<String, String> getProperties() {
        return properties;
    }

    public Map<String, List<Integer>> getRows() {
        return rows;
    }

    @Override
    public String toString() {
        return "MergedDataSet{" +
                "header='" + header + '\'' +
                ", mappedCfgBlockName='" + mappedCfgBlockName + '\'' +
                ", type='" + type + '\'' +
                ", cols=" + cols +
                ", properties=" + properties +
                ", rows=" + rows +
                '}';
    }
}
