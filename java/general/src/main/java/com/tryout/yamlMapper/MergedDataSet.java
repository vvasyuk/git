package com.tryout.yamlMapper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MergedDataSet {
    String headerText;
    Boolean header;
    String mappedCfgBlockName;
    String type;
    Integer cols;
    LinkedHashMap<String, Object> properties;
    Map<String, List<String>> rows;

    public void setHeader(Boolean header) {
        this.header = header;
    }

    public Boolean getHeader() {
        return header;
    }

    public void setHeaderText(String headerText) {
        this.headerText = headerText;
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

    public void setProperties(LinkedHashMap<String, Object> properties) {
        this.properties = properties;
    }

    public void setRows(Map<String, List<String>> rows) {
        this.rows = rows;
    }

    public String getHeaderText() {
        return headerText;
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

    public LinkedHashMap<String, Object> getProperties() {
        return properties;
    }

    public Map<String, List<String>> getRows() {
        return rows;
    }
}
