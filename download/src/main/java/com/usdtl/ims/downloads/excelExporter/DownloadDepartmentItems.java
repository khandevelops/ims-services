package com.usdtl.ims.downloads.excelExporter;

import com.usdtl.ims.clients.responseClass.DepartmentDownloadResponse;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.time.DateFormatUtils;
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

@AllArgsConstructor
public class DownloadDepartmentItems {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    private List<DepartmentDownloadResponse> departmentDownloadResponseItems;

    public DownloadDepartmentItems(List<DepartmentDownloadResponse> departmentDownloadResponseItems) {
        this.departmentDownloadResponseItems = departmentDownloadResponseItems;
        workbook = new XSSFWorkbook();
    }

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
        createCell(row, 4, "Recent Vendor", style);
        createCell(row, 5, "Average Unit Price", style);
        createCell(row, 6, "Category", style);
        createCell(row, 7, "Comment", style);
        createCell(row, 8, "Usage Level", style);
        createCell(row, 9, "Min Qty", style);
        createCell(row, 10, "Max Qty", style);
        createCell(row, 11, "Lot Number", style);
        createCell(row, 12, "Location", style);
        createCell(row, 13, "expiration_date", style);
        createCell(row, 14, "received_date", style);
        createCell(row, 15, "Quantity", style);
        createCell(row, 16, "Total Price", style);
        createCell(row, 17, "Total Quantity", style);
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if(value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if(value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if(value instanceof Long) {
            cell.setCellValue((Long) value);
        } else if(value instanceof Double) {
            cell.setCellValue((Double) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(10);
        style.setFont(font);

        for(DepartmentDownloadResponse departmentDownloadResponseItem : departmentDownloadResponseItems) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, departmentDownloadResponseItem.getItem(), style);
            createCell(row, columnCount++, departmentDownloadResponseItem.getPurchase_unit(), style);
            createCell(row, columnCount++, departmentDownloadResponseItem.getPart_number(), style);
            createCell(row, columnCount++, departmentDownloadResponseItem.getRecent_cn(), style);
            createCell(row, columnCount++, departmentDownloadResponseItem.getRecent_vendor(), style);
            createCell(row, columnCount++, departmentDownloadResponseItem.getAverage_unit_price(), style);
            createCell(row, columnCount++, (departmentDownloadResponseItem.getCategory()).toString(), style);
            createCell(row, columnCount++, departmentDownloadResponseItem.getComment(), style);
            createCell(row, columnCount++, departmentDownloadResponseItem.getUsage_level(), style);
            createCell(row, columnCount++, departmentDownloadResponseItem.getMin_quantity(), style);
            createCell(row, columnCount++, departmentDownloadResponseItem.getMax_quantity(), style);
            createCell(row, columnCount++, departmentDownloadResponseItem.getLot_number(), style);
            createCell(row, columnCount++, departmentDownloadResponseItem.getLocation(), style);
            createCell(row, columnCount++, departmentDownloadResponseItem.getExpirations_date() == null ? null : DateFormatUtils.format(departmentDownloadResponseItem.getExpirations_date(), "yyyy-MM-dd HH:mm:SS"), style);
            createCell(row, columnCount++, departmentDownloadResponseItem.getReceived_date() == null ? null : DateFormatUtils.format(departmentDownloadResponseItem.getReceived_date(), "yyyy-MM-dd HH:mm:SS"), style);
            createCell(row, columnCount++, departmentDownloadResponseItem.getQuantity(), style);
            createCell(row, columnCount++, departmentDownloadResponseItem.getTotal_price(), style);
            createCell(row, columnCount++, departmentDownloadResponseItem.getTotal_quantity(), style);
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
