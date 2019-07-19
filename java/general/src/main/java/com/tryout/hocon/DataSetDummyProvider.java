package com.tryout.hocon;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.tryout.yamlMapper.MergedDataSet;
import com.typesafe.config.Config;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataSetDummyProvider {
    public static DataSet getDummyDataSet(Config conf, String blockName){
        LinkedHashMap<String, LinkedHashMap<String, List<String>>> dummyData = new LinkedHashMap<>();
        DataSet ds = new DataSet();

        conf.getConfig(blockName).getList("order").forEach(table->{
            LinkedHashMap<String, List<String>> rowsInnerMap = new LinkedHashMap<>();
            String tableName = table.unwrapped().toString();

            List<String> tableRowsList = getRowsList().get(tableName);

            for(String row : tableRowsList){
                List<String> listOfCols;
                if(" ".equals(row)){
                    listOfCols = Stream.of("a", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b").collect(Collectors.toList());
                }else{
                    listOfCols = getListOfRandomInts(conf.getConfig(blockName).getConfig(tableName).getInt("cols")-1);
                }

                rowsInnerMap.put(row,listOfCols);
                dummyData.put(tableName, rowsInnerMap);
            }

        });
        ds.setBlockName(blockName);
        ds.setTablesData(java.util.Optional.of(dummyData));
        return ds;
    }

    private static LinkedHashMap<String, List<String>> getRowsList() {
        List<String> rowsA= Stream.of(" ", "aRow1", "aRow2", "Total aRow3").collect(Collectors.toList());
        List<String> rowsB=Stream.of("bRow1", "bRow2").collect(Collectors.toList());
        List<String> rowsC=Stream.of("cRow1").collect(Collectors.toList());
        List<String> rowsD=Stream.of("dRow1","dRow2","dRow3","dRow4").collect(Collectors.toList());
        LinkedHashMap<String, List<String>> result = new LinkedHashMap<>();

        result.put("tab1", rowsA);
        result.put("tab2", rowsB);
        result.put("tab3", rowsC);
        return result;
    }

    private static String getRandomNumberInRange(Random r, int min, int max) {
        return String.valueOf(r.nextInt((max - min) + 1) + min);
    }

    private static List<String> getListOfRandomInts(Integer cols) {
        List<String> res = new ArrayList<>();
        Random r = new Random();
        for (int i=0; i<cols; i++){
            res.add(getRandomNumberInRange(r, -50, 50));
        }
        return res;
    }
}
