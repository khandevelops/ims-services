package com.usdtl.ims.departmentMaster.extractionsMaster;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ExtractionsMasterExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    private ExtractionsMasterRepository repository;

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Extractions Master Items");
        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(12);
        style.setFont(font);

        createCell(row, 0, "Item", style);
        createCell(row, 1, "Purchase Unit", style);
        createCell(row, 2, "Part Number", style);
        createCell(row, 3, "Recent CN", style);
        createCell(row, 4, "Total Quantity", style);
        createCell(row, 5, "Quantity", style);
        createCell(row, 6, "Usage Level", style);
        createCell(row, 7, "Min Qty", style);
        createCell(row, 8, "Max Qty", style);
        createCell(row, 9, "Order Qty", style);
        createCell(row, 10, "Unit Price", style);
        createCell(row, 11, "Total Price", style);
        createCell(row, 12, "Comment", style);
        createCell(row, 13, "Location", style);
        createCell(row, 14, "Category", style);
        createCell(row, 15, "Lot Number", style);
        createCell(row, 15, "Expiration Date", style);
        createCell(row, 15, "Received Date", style);
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if(value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if(value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        List<ExtractionsMasterEntity> extractionsMasterItems = (List<ExtractionsMasterEntity>) repository.findAll();
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(10);
        style.setFont(font);

        for(ExtractionsMasterEntity extractionsMasterItem : extractionsMasterItems) {
            Row row = sheet.createRow(rowCount++);
            int columCount = 0;

            createCell(row, columCount, extractionsMasterItem.getMasterItem().getItem(), style);
            createCell(row, columCount, extractionsMasterItem.getMasterItem().getItem(), style);
            createCell(row, columCount, extractionsMasterItem.getMasterItem().getItem(), style);
            createCell(row, columCount, extractionsMasterItem.getMasterItem().getItem(), style);
            createCell(row, columCount, extractionsMasterItem.getMasterItem().getItem(), style);
            createCell(row, columCount, extractionsMasterItem.getMasterItem().getItem(), style);
            createCell(row, columCount, extractionsMasterItem.getMasterItem().getItem(), style);
            createCell(row, columCount, extractionsMasterItem.getMasterItem().getItem(), style);
            createCell(row, columCount, extractionsMasterItem.getMasterItem().getItem(), style);
            createCell(row, columCount, extractionsMasterItem.getMasterItem().getItem(), style);
            createCell(row, columCount, extractionsMasterItem.getMasterItem().getItem(), style);
            createCell(row, columCount, extractionsMasterItem.getMasterItem().getItem(), style);
            createCell(row, columCount, extractionsMasterItem.getMasterItem().getItem(), style);
            createCell(row, columCount, extractionsMasterItem.getMasterItem().getItem(), style);

        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
