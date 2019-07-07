package com.tryout.yamlMapper;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class YamlMapper {

    public void execute() {
        System.out.println("starting yaml mapper");

//        Yaml yaml = new Yaml();
//        InputStream inputStream = null;
//        try {
//            inputStream = Files.newInputStream(Paths.get("D:\\work\\tryout\\java\\general\\src\\main\\resources\\foobar.yaml"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        FooBar fooBar = yaml.loadAs( inputStream, FooBar.class );
//
//        System.out.println(fooBar.getBar());
//        System.out.println(fooBar.getAddress().get("city"));
//        System.out.println(fooBar.getRoles()[0]);
//
//        System.out.println(fooBar.getF1().get("K1"));
//        System.out.println(fooBar.getB1().get("K2"));

        LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, Object>>>> cfg = createConfig();
//        System.out.println(cfg.get("root").get("cat1").get("blockA").get("type"));
//        System.out.println(cfg.get("root").get("cat1").get("blockA").get("cols"));

        DataSet dummyData = createDummyDataSet(cfg);

        mergeDataAndConfig(dummyData, cfg);

        System.out.println("End");
    }

    private LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, Object>>>> createConfig() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("report.yaml");
        Yaml yaml = new Yaml();
        return yaml.load(inputStream);
    }

    private void mergeDataAndConfig(DataSet dummyData, LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, Object>>>> cfg) {
        System.out.println("mergeDataAndConfig");
        System.out.println("category from data: " + dummyData.getType());

        LinkedHashMap<String, LinkedHashMap<String, Object>> catCfg = cfg.get("root").get(dummyData.getType());

        Iterator<Map.Entry<String, LinkedHashMap<String, Object>>> cfgIter = catCfg.entrySet().iterator();
        Iterator<Map.Entry<String, Map<String, List<Integer>>>> dataIter = dummyData.getData().entrySet().iterator();
        while(dataIter.hasNext()){
            Map.Entry<String, Map<String, List<Integer>>> data = dataIter.next();
            Map.Entry<String, LinkedHashMap<String, Object>> c = cfgIter.next();

            System.out.println("block: " + data.getKey() + " | "
                    + "cfg: " + c.getKey() + " | "
                    + "type: " + c.getValue().get("type") + " | "
                    + "cols: " + c.getValue().get("cols") + " | "
                    + "props: " + ((LinkedHashMap<String, String>)c.getValue().get("properties")).keySet()
            );

            for (String k : data.getValue().keySet()){
                System.out.println("row: " + k + " | "
                        + "cells: " + data.getValue().get(k) + " | ");
            }
        }

    }

    private DataSet createDummyDataSet(LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, Object>>>> cfg){
        Map<String, Map<String, List<Integer>>> dummyData = new LinkedHashMap<>();
        DataSet ds = new DataSet();

        List<String> blockList = getBlocksList();
        LinkedHashMap<String, LinkedHashMap<String, Object>> c = cfg.get("root").get("cat1");

        Iterator<String> blockIter = blockList.iterator();
        Iterator<Map.Entry<String, LinkedHashMap<String, Object>>> cfgIter = c.entrySet().iterator();
        Iterator<List<String>> rowsIter = getListofRows().iterator();
        
        while (blockIter.hasNext()){
            String blockNext = blockIter.next();
            LinkedHashMap<String, Object> cfgNext = cfgIter.next().getValue();
            LinkedHashMap<String, List<Integer>> rowsMap = new LinkedHashMap();
            Map<String, List<Integer>> rowsInnerMap = new LinkedHashMap<>();

            for(String row:rowsIter.next()){
                List<Integer> listOfInts = getListOfRandomInts((Integer) cfgNext.get("cols")-1);
                rowsInnerMap.put(row,listOfInts);
                dummyData.put(blockNext, rowsInnerMap);
//                System.out.println("header:" + s + "|" + "type:" + configured.get("type") + "|" + "cols:" + configured.get("cols") + "|" + "row:" + row + "|" + "listOfInts:" + listOfInts);
            }
        }
        
        ds.setType("cat1");
        ds.setData(dummyData);
        return ds;
    }

    private List<List<String>> getListofRows() {
        List<String> rowsA= Stream.of("aRow1", "aRow2", "aRow3").collect(Collectors.toList());
        List<String> rowsB=Stream.of("bRow1", "bRow2").collect(Collectors.toList());
        List<String> rowsC=Stream.of("cRow1").collect(Collectors.toList());
        List<String> rowsD=Stream.of("dRow1","dRow2","dRow3","dRow4").collect(Collectors.toList());
        List<List<String>> listofOfRows = new ArrayList<>();
        listofOfRows.add(rowsA);
        listofOfRows.add(rowsB);
        listofOfRows.add(rowsC);
        listofOfRows.add(rowsD);
        return listofOfRows;
    }

    private List<String> getBlocksList() {
        return Stream.of("b1", "b2", "b3").collect(Collectors.toList());
    }

    private List<Integer> getListOfRandomInts(Integer cols) {
        List<Integer> res = new ArrayList<>();

        for (int i=0; i<cols; i++){
            res.add(getRandomNumberInRange(-50, 50));
        }
        return res;
    }

    private static int getRandomNumberInRange(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    private void buildTableFromConfig(LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, Object>>>> cfg){

    }
}