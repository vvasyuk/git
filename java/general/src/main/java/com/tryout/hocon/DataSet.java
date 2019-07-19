package com.tryout.hocon;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

public class DataSet {
    String blockName;
    Optional<LinkedHashMap<String, LinkedHashMap<String, List<String>>>> tablesData;

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public void setTablesData(Optional<LinkedHashMap<String, LinkedHashMap<String, List<String>>>> tablesData) {
        this.tablesData = tablesData;
    }

    public String getBlockName() {
        return blockName;
    }

    public Optional<LinkedHashMap<String, LinkedHashMap<String, List<String>>>> getTablesData() {
        return tablesData;
    }
}
