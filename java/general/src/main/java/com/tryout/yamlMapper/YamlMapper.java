package com.tryout.yamlMapper;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.yaml.snakeyaml.Yaml;

import java.io.FileOutputStream;
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

        List<MergedDataSet> listMergedDataSet = mergeDataAndConfig(dummyData, cfg);

        buildPdf(listMergedDataSet);

        listMergedDataSet.forEach(x-> System.out.println(x));
    }

    private LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, Object>>>> createConfig() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("report.yaml");
        Yaml yaml = new Yaml();
        return yaml.load(inputStream);
    }

    private List<MergedDataSet> mergeDataAndConfig(DataSet dummyData, LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, Object>>>> cfg) {
//        System.out.println("mergeDataAndConfig");
//        System.out.println("category from data: " + dummyData.getType());

        List<MergedDataSet> listMergedDataSet = new LinkedList<>();

        Iterator<Map.Entry<String, LinkedHashMap<String, Object>>> cfgIter = cfg.get("root").get(dummyData.getType()).entrySet().iterator();
        Iterator<Map.Entry<String, Map<String, List<Integer>>>> dataIter = dummyData.getData().entrySet().iterator();
        while(dataIter.hasNext()){
            Map.Entry<String, Map<String, List<Integer>>> data = dataIter.next();
            Map.Entry<String, LinkedHashMap<String, Object>> c = cfgIter.next();

            MergedDataSet mergedDataSet = new MergedDataSet();
            mergedDataSet.setHeader(data.getKey());
            mergedDataSet.setMappedCfgBlockName(c.getKey());
            mergedDataSet.setType((String) c.getValue().get("type"));
            mergedDataSet.setCols((Integer) c.getValue().get("cols"));
            mergedDataSet.setProperties((LinkedHashMap<String, String>)c.getValue().get("properties"));
            mergedDataSet.setRows(data.getValue());
            listMergedDataSet.add(mergedDataSet);
//            System.out.println("block: " + data.getKey() + " | "
//                    + "cfg: " + c.getKey() + " | "
//                    + "type: " + c.getValue().get("type") + " | "
//                    + "cols: " + c.getValue().get("cols") + " | "
//                    + "props: " + ((LinkedHashMap<String, String>)c.getValue().get("properties")).keySet()
//            );
//
//            for (String k : data.getValue().keySet()){
//                System.out.println("row: " + k + " | "
//                        + "cells: " + data.getValue().get(k) + " | ");
//            }
        }
        return listMergedDataSet;
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

    private void buildPdf(List<MergedDataSet> listMergedDataSet){
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("Hello.pdf"));
            document.open();
            for(MergedDataSet mergedDataSet : listMergedDataSet){
                addTable(document, 3, mergedDataSet.getHeader(), mergedDataSet.getRows());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        document.close();
    }

    private void addTable(Document document, Integer cols, String header, Map<String, List<Integer>> rows) throws DocumentException {
        PdfPTable table = new PdfPTable(cols);
        PdfPCell headerCell = getHeader(cols, header);
        table.addCell(headerCell);

        document.add(table);
    }

    private PdfPCell getHeader(Integer cols, String header){
        PdfPCell cell = new PdfPCell((new Paragraph(header+ " " + cols)));
        cell.setColspan(cols);
        return  cell;
    }
}