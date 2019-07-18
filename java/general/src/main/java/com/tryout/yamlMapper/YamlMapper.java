package com.tryout.yamlMapper;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.yaml.snakeyaml.Yaml;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class YamlMapper {
    public static final Font FONT_DEFAULT = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.NORMAL, BaseColor.BLACK);
    public static final Font FONT_RED = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.NORMAL, BaseColor.RED);
    public static final Font FONT_TITLE= new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD, BaseColor.BLACK);
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

        LinkedHashMap<String,
                LinkedHashMap<String,
                        LinkedHashMap<String,
                                LinkedHashMap<String, Object>>>> cfg = createConfig();
//        System.out.println(cfg.get("root").get("cat1").get("blockA").get("type"));
//        System.out.println(cfg.get("root").get("cat1").get("blockA").get("cols"));

        DataSet dummyData = createDummyDataSet(cfg);

        List<MergedDataSet> listMergedDataSet = mergeDataAndConfig(dummyData, cfg);

        buildPdf(listMergedDataSet);

//        listMergedDataSet.forEach(x-> System.out.println(x));
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
        Iterator<Map.Entry<String, LinkedHashMap<String, List<String>>>> dataIter = dummyData.getData().entrySet().iterator();
        while(dataIter.hasNext()){
            Map.Entry<String, LinkedHashMap<String, List<String>>> data = dataIter.next();
            Map.Entry<String, LinkedHashMap<String, Object>> c = cfgIter.next();

            MergedDataSet mergedDataSet = new MergedDataSet();
            mergedDataSet.setHeaderText(data.getKey());
            mergedDataSet.setHeader((Boolean) c.getValue().get("header"));
            mergedDataSet.setMappedCfgBlockName(c.getKey());
            mergedDataSet.setType((String) c.getValue().get("type"));
            mergedDataSet.setCols((Integer) c.getValue().get("cols"));
            mergedDataSet.setProperties((LinkedHashMap<String, Object>)c.getValue().get("properties"));
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
        LinkedHashMap<String, LinkedHashMap<String, List<String>>> dummyData = new LinkedHashMap<>();
        DataSet ds = new DataSet();

        List<String> blockList = getBlocksList();
        LinkedHashMap<String, LinkedHashMap<String, Object>> c = cfg.get("root").get("cat1");

        Iterator<String> blockIter = blockList.iterator();
        Iterator<Map.Entry<String, LinkedHashMap<String, Object>>> cfgIter = c.entrySet().iterator();
        Iterator<List<String>> rowsIter = getListofRows().iterator();
        
        while (blockIter.hasNext()){
            String blockNext = blockIter.next();
            LinkedHashMap<String, Object> cfgNext = cfgIter.next().getValue();
            LinkedHashMap<String, List<String>> rowsInnerMap = new LinkedHashMap<>();

            for(String row:rowsIter.next()){
                List<String> listOfCols;
                if(" ".equals(row)){
                    listOfCols = Stream.of("a", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b").collect(Collectors.toList());
                }else{
                    listOfCols = getListOfRandomInts((Integer) cfgNext.get("cols")-1);
                }

                rowsInnerMap.put(row,listOfCols);
                dummyData.put(blockNext, rowsInnerMap);
//                System.out.println("header:" + s + "|" + "type:" + configured.get("type") + "|" + "cols:" + configured.get("cols") + "|" + "row:" + row + "|" + "listOfInts:" + listOfInts);
            }
        }
        
        ds.setType("cat1");
        ds.setData(dummyData);
        return ds;
    }

    private List<List<String>> getListofRows() {
        List<String> rowsA= Stream.of(" ", "aRow1", "aRow2", "Total aRow3").collect(Collectors.toList());
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

    private List<String> getListOfRandomInts(Integer cols) {
        List<String> res = new ArrayList<>();
        Random r = new Random();
        for (int i=0; i<cols; i++){
            res.add(getRandomNumberInRange(r, -50, 50));
        }
        return res;
    }

    private static String getRandomNumberInRange(Random r,int min, int max) {
        return String.valueOf(r.nextInt((max - min) + 1) + min);
    }

    private void buildPdf(List<MergedDataSet> listMergedDataSet){
        Document document = new Document(PageSize.A4.rotate());
        try {
            PdfWriter.getInstance(document, new FileOutputStream("Hello.pdf"));
            document.open();
            document.newPage();
            for(MergedDataSet mergedDataSet : listMergedDataSet){
                addTable(document, mergedDataSet);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        document.close();
    }

    private void addTable(Document document, MergedDataSet mergedDataSet) throws DocumentException {
        int cols = mergedDataSet.getCols();
        Boolean header = mergedDataSet.getHeader();
        String headerText = mergedDataSet.getHeaderText();
        LinkedHashMap<String, Object> properties = mergedDataSet.getProperties();
        Map<String, List<String>> rows = mergedDataSet.getRows();

        float defaultTableWidth = 800f;
        float defaultCellWidth = 29;

        PdfPTable table = new PdfPTable(cols);
        table.setTotalWidth(defaultTableWidth);
        table.setLockedWidth(true);
        table.setPaddingTop(1f);
        table.setWidths(getColumnWidths(cols, defaultTableWidth, defaultCellWidth));

        if(header){
            table.addCell(cellFormatter(headerText, (LinkedHashMap<String, String>) properties.get("Header")));
        }

        rows.keySet().forEach(row->{
            String rowType="";
            rowType = "".equals(rowType) && row.startsWith("Total ")?"TotalCell": rowType;
            rowType = "".equals(rowType) && row.startsWith(" ")?"2RowCell": rowType;
            rowType = "".equals(rowType)?"Cell": rowType;

            table.addCell(cellFormatter(row, (LinkedHashMap<String, String>) properties.get("FirstCol"+rowType)));
            String finalRowType = rowType;
            rows.get(row).forEach(x->{
                table.addCell(cellFormatter(x, (LinkedHashMap<String, String>) properties.get("MidCol"+ finalRowType)));
            });
        });

        table.setSpacingAfter(10f);

        applyCustomParams(table, properties);

        document.add(table);
    }

    private void applyCustomParams(PdfPTable table, LinkedHashMap<String, Object> properties) {
        if(properties.get("Custom")!=null){
            LinkedHashMap<String, LinkedHashMap<String, String>> custom = (LinkedHashMap<String, LinkedHashMap<String, String>>)properties.get("Custom");

            custom.forEach((x,y)->{
                y.forEach((n,v)->{
                    String[] coordinates = x.split(",");
//                    System.out.println("x:" + coordinates[0] + " y:" + coordinates[1]);
//                    System.out.println(n + ":" + v);
                    PdfPCell cell = table.getRow(Integer.parseInt(coordinates[0])).getCells()[Integer.parseInt(coordinates[1])];
                    Paragraph p = (Paragraph) cell.getCompositeElements().get(0);

                    if("AllFont".equals(n)){
                        String s = p.getContent();
                        int align = p.getAlignment();
                        p.clear();
                        Paragraph nP = new Paragraph(s, getAllFont(v));
                        nP.setAlignment(align);
                        cell.addElement(nP);
                    }

                    if("Alignment".equals(n)){
                        p.setAlignment(Integer.parseInt(v));
                    }



                });

            });

        }
    }

    private float[] getColumnWidths(int cols, float defaultTableWidth, float defaultColWidth) {
        float[] columnWidths = new float[cols];
        columnWidths[0]=defaultTableWidth - (cols-1)*defaultColWidth;
        for (int i=0; i<cols; i++){
            columnWidths[i]=defaultColWidth;
        }
        return columnWidths;
    }

    private PdfPCell cellFormatter(String text, LinkedHashMap<String, String> properties) {
        String background = properties.getOrDefault("Background", null);
        Integer fontStyle = Integer.valueOf(properties.getOrDefault("FontStyle", "0"));
        String fontColor = properties.getOrDefault("FontColor", null);
        String negFontColor = properties.getOrDefault("NegFontColor", null);

        PdfPCell cell = new PdfPCell();
        Paragraph element;

        if("cell".equals(properties.get("Type")) || "totalCell".equals(properties.get("Type"))){
            Integer value = Integer.valueOf(text);
            element = new Paragraph(text, value>=0 ? getFont(fontStyle, fontColor) : getFont(fontStyle, negFontColor));
        }else{
            element = new Paragraph(text, getFont(fontStyle, fontColor));
        }
        properties.getOrDefault("Alignment", "5");
        cell.setUseAscender(Boolean.parseBoolean(properties.getOrDefault("UseAscender", "false")));
        cell.setUseDescender(Boolean.parseBoolean(properties.getOrDefault("UseDescender", "false")));
        cell.setPadding(Float.parseFloat(properties.getOrDefault("Padding", "2f")));
        cell.setVerticalAlignment(Integer.parseInt(properties.getOrDefault("VerticalAlignment", "5")));
        cell.setBackgroundColor(getColor(background));
        cell.setColspan(Integer.parseInt(properties.getOrDefault("Colspan", "0")));
        element.setAlignment(Integer.parseInt(properties.getOrDefault("Alignment", "5")));
        cell.addElement(element);

        return cell;
    }

    private Font getFont(Integer fontStyle, String fontColor) {
        return new Font(Font.FontFamily.TIMES_ROMAN, 8, fontStyle, getColor(fontColor));
    }

    private Font getAllFont(String allFont) {
        String[] splitted = allFont.split(",");
        return new Font(Font.FontFamily.valueOf(splitted[0])
                , Integer.valueOf(splitted[1])
                , Integer.valueOf(splitted[2])
                , getColor(Integer.valueOf(splitted[3]),Integer.valueOf(splitted[4]),Integer.valueOf(splitted[5])));
    }

    private BaseColor getColor(Integer s, Integer s1, Integer s2) {
        return new BaseColor(s, s1, s2);
    }

    private Font getAllFont(Integer size, Integer fontStyle, String fontColor) {
        return new Font(Font.FontFamily.TIMES_ROMAN, size, fontStyle, getColor(fontColor));
    }

    private BaseColor getColor(String fontColor) {
        if(fontColor==null){
            return null;
        }
        String[] colors = fontColor.split(",");
        return new BaseColor(Integer.valueOf(colors[0]), Integer.valueOf(colors[1]), Integer.valueOf(colors[2]));
    }

    private PdfPCell getHeader(Integer cols, String header){
        PdfPCell cell = new PdfPCell((new Paragraph("header: " + header+ " column number: " + cols)));
        cell.setColspan(cols);
        return  cell;
    }
}