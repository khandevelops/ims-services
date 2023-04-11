package com.usdtl.ims.download.department.excelExporter;

import com.usdtl.ims.clients.responseClass.DepartmentDownloadResponse;
import com.usdtl.ims.download.department.extractions.ExtractionsService;
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
import java.text.Format;
import java.text.SimpleDateFormat;
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
        createCell(row, 3, "Recent Vendor", style);
        createCell(row, 10, "Average Unit Price", style);
        createCell(row, 14, "Category", style);
        createCell(row, 12, "Comment", style);
        createCell(row, 6, "Usage Level", style);
        createCell(row, 7, "Min Qty", style);
        createCell(row, 8, "Max Qty", style);
        createCell(row, 15, "Lot Number", style);
        createCell(row, 13, "Location", style);
        createCell(row, 15, "Received Date", style);
        createCell(row, 15, "Expiration Date", style);
        createCell(row, 5, "Quantity", style);
        createCell(row, 9, "Order Qty", style);
        createCell(row, 11, "Total Price", style);
        createCell(row, 4, "Total Quantity", style);
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
            int columCount = 0;
            Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            createCell(row, columCount, departmentDownloadResponseItem.getItem(), style);
            createCell(row, columCount, departmentDownloadResponseItem.getPurchase_unit(), style);
            createCell(row, columCount, departmentDownloadResponseItem.getPart_number(), style);
            createCell(row, columCount, departmentDownloadResponseItem.getRecent_cn(), style);
            createCell(row, columCount, departmentDownloadResponseItem.getRecent_vendor(), style);
            createCell(row, columCount, departmentDownloadResponseItem.getAverage_unit_price(), style);
            createCell(row, columCount, (departmentDownloadResponseItem.getCategory()).toString(), style);
            createCell(row, columCount, departmentDownloadResponseItem.getComment(), style);
            createCell(row, columCount, departmentDownloadResponseItem.getUsage_level(), style);
            createCell(row, columCount, departmentDownloadResponseItem.getMin_quantity(), style);
            createCell(row, columCount, departmentDownloadResponseItem.getMax_quantity(), style);
            createCell(row, columCount, departmentDownloadResponseItem.getLot_number(), style);
            createCell(row, columCount, departmentDownloadResponseItem.getLocation(), style);
            createCell(row, columCount, departmentDownloadResponseItem.getReceived_date(), style);
            createCell(row, columCount, DateFormatUtils.format(departmentDownloadResponseItem.getExpirations_date(), "dd MMM yyyy HH:mm:ss Z"), style);
            createCell(row, columCount, DateFormatUtils.format(departmentDownloadResponseItem.getQuantity(), "dd MMM yyyy HH:mm:ss Z"), style);
            createCell(row, columCount, departmentDownloadResponseItem.getOrder_quantity(), style);
            createCell(row, columCount, departmentDownloadResponseItem.getTotal_price(), style);
            createCell(row, columCount, departmentDownloadResponseItem.getTotal_quantity(), style);
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
