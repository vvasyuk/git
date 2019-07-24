package com.tryout.hocon;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.tryout.yamlMapper.MergedDataSet;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigValue;

import java.io.FileOutputStream;
import java.util.*;
import java.util.List;

public class HoconMapper {
    public void execute(){
        Config conf = ConfigFactory.load("report.conf");

//        System.out.println(conf.getConfigList("blockList").get(0).getConfig("tab1").getString("name"));
//        System.out.println(conf.getConfigList("blockList").get(0).getList("order"));

        String block1Name = "block1";
        DataSet block1DummyData = DataSetDummyProvider.getDummyDataSet(conf, block1Name);

        Document document = new Document(PageSize.A4.rotate());
        try {
            PdfWriter.getInstance(document, new FileOutputStream("Hello.pdf"));
            document.open();
            document.newPage();

            appendPdfWithBlock(document, block1Name, block1DummyData, conf.getConfig(block1Name));

        } catch (Exception e) {
            e.printStackTrace();
        }
        document.close();
    }

    public void appendPdfWithBlock(Document document,String blockName, DataSet blockData, Config blockConfig) throws DocumentException {

        for (ConfigValue table : blockConfig.getList("order")) {
            String tableName = table.unwrapped().toString();
            Config tableConfig = blockConfig.getConfig(tableName);
            LinkedHashMap<String, List<String>> rowsData = blockData.getTablesData().get().get(tableName);
            appendPdfWithTable(document, blockName, tableName, rowsData, tableConfig);
        }
    }

    public void appendPdfWithTable(Document document, String blockName, String tableName, LinkedHashMap<String, List<String>> rowsData, Config tableConfig) throws DocumentException {
        int cols = tableConfig.getInt("cols");
        Config properties = tableConfig.getConfig("properties");
        float defaultTableWidth = 800f;
        float defaultCellWidth = 29;

        PdfPTable table = new PdfPTable(cols);
        table.setTotalWidth(defaultTableWidth);
        table.setLockedWidth(true);
        table.setPaddingTop(1f);
        table.setWidths(getColumnWidths(cols, defaultTableWidth, defaultCellWidth));

        rowsData.keySet().forEach(row->{
            String rowType="";
            rowType = "".equals(rowType) && row.startsWith("Total ")?"TotalCell": rowType;
            rowType = "".equals(rowType) && row.startsWith(" ")?"2RowCell": rowType;
            rowType = "".equals(rowType)?"Cell": rowType;

            table.addCell(cellFormatter(row, properties.getConfig("FirstCol"+rowType)));
            String finalRowType = rowType;
            rowsData.get(row).forEach(x->{
                table.addCell(cellFormatter(x, properties.getConfig("MidCol"+finalRowType)));
            });
        });

        table.setSpacingAfter(10f);
        if (tableConfig.hasPath("customProperties")){
            applyCustomParams(table, tableConfig.getConfig("customProperties"));
        }

        document.add(table);
    }
    private void applyCustomParams(PdfPTable table, Config properties) {
        Set<Map.Entry<String, ConfigValue>> entries = properties.entrySet();
        table.getRows().forEach(row->{
            PdfPCell[] cells = row.getCells();
            Paragraph firstColText = (Paragraph) cells[0].getCompositeElements().get(0);
            entries.stream().forEach(x-> {
                String value = (String) x.getValue().unwrapped();
                String[] rangesValue = x.getKey().replaceAll("\"\\.", ",").replaceAll("\"", "").split(",");
                //System.out.println(rangesValue[0] + "###" + rangesValue[1] + "###" + rangesValue[2]+ " = " +value);
                if (rangesValue[0].equals(firstColText.getContent())){
                    String[] cellRange = rangesValue[1].split("\\..");
                    int start = Integer.parseInt(cellRange[0]);
                    int end = cellRange[1].equals("n") ? cells.length : Integer.parseInt(cellRange[1]);
                    List<PdfPCell> subList = Arrays.asList(cells).subList(start, end);
                    subList.forEach(cell->{
                        Paragraph p = (Paragraph)cell.getCompositeElements().get(0);
                        if("Alignment".equals(rangesValue[2])){
                            p.setAlignment(Integer.parseInt(value));
                        }
                        if("AllFont".equals(rangesValue[2])){
                            String s = p.getContent();
                            int align = p.getAlignment();
                            p.clear();
                            Paragraph nP = new Paragraph(s, getAllFont(value));
                            nP.setAlignment(align);
                            cell.addElement(nP);
                        }
//                        if("BorderWidth".equals(rangesValue[2])){
//                            cell.setBorderWidth(Float.parseFloat(value));
//                        }
                        if("BorderWidthBottom".equals(rangesValue[2])){
                            cell.setBorderWidthBottom(Float.parseFloat(value));
                        }
                        if("BorderWidthTop".equals(rangesValue[2])){
                            cell.setBorderWidthTop(Float.parseFloat(value));
                        }
                        if("BorderWidthLeft".equals(rangesValue[2])){
                            cell.setBorderWidthLeft(Float.parseFloat(value));
                        }
                        if("BorderWidthRight".equals(rangesValue[2])){
                            cell.setBorderWidthRight(Float.parseFloat(value));
                        }
                    });
                }
            });
        });
    }
    private float[] getColumnWidths(int cols, float defaultTableWidth, float defaultColWidth) {
        float[] columnWidths = new float[cols];
        columnWidths[0]=defaultTableWidth - (cols-1)*defaultColWidth;
        for (int i=0; i<cols; i++){
            columnWidths[i]=defaultColWidth;
        }
        return columnWidths;
    }

    private PdfPCell cellFormatter(String text, Config properties) {
        String negAllFont = properties.getString("NegFontColor");
        String allFont = properties.getString("AllFont");

        PdfPCell cell = new PdfPCell();
        Paragraph element;

        if("Cell".equals(properties.getString("Type")) || "TotalCell".equals(properties.getString("Type"))){
            Integer value = Integer.valueOf(text);
            element = new Paragraph(text, value>=0 ? getAllFont(allFont) : getAllFont(negAllFont));
        }else{
            element = new Paragraph(text, getAllFont(allFont));
        }

        cell.setUseAscender(properties.getBoolean("UseAscender"));
        cell.setUseDescender(properties.getBoolean("UseDescender"));
        cell.setPadding(Float.parseFloat(properties.getString("Padding")));
        cell.setVerticalAlignment(properties.getInt("VerticalAlignment"));
        cell.setBackgroundColor(getColor(properties.getString("Background")));
        cell.setColspan(Integer.parseInt(properties.getString("Colspan")));
        element.setAlignment(properties.getInt("Alignment"));
        cell.addElement(element);

        return cell;
    }

    private Font getAllFont(String allFont) {
        String[] splitted = allFont.split(",");
        return new Font(Font.FontFamily.valueOf(splitted[0]), Integer.valueOf( splitted[1]),
                Integer.valueOf( splitted[2]), getColor(splitted[3], splitted[4], splitted[5]));
    }
    private BaseColor getColor(String s1,String s2,String s3) {
        return new BaseColor(Integer.valueOf(s1), Integer.valueOf(s2), Integer.valueOf(s3));
    }

    private BaseColor getColor(String fontColor) {
        if(fontColor==null){
            return null;
        }
        String[] colors = fontColor.split(",");
        return new BaseColor(Integer.valueOf(colors[0]), Integer.valueOf(colors[1]), Integer.valueOf(colors[2]));
    }
}
