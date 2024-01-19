package com.bookrental.bookrental.helpers;

import com.bookrental.bookrental.pojo.trasaction.BookTransactionResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class Helper {

    public static String[] HEADERS = {"ID", "Code", "From Date", "To Date", "Book", "Member", "Rent Status"};
    public static String SHEET_NAME = "book-transaction";

    public static ByteArrayInputStream dataToExcel(List<BookTransactionResponse> list) throws IOException {

        // create work book

        Workbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            Sheet sheet = workbook.createSheet(SHEET_NAME);

            sheet.setDefaultColumnWidth(20);
            // create row : header
            Row row = sheet.createRow(0);
            for (int i = 0; i < HEADERS.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(HEADERS[i]);
            }
            // create column : values

            int rowIndex = 1;
            for (BookTransactionResponse b : list) {
                Row dataRow = sheet.createRow(rowIndex);
                rowIndex++;
                dataRow.createCell(0).setCellValue(b.getId());
                dataRow.createCell(1).setCellValue(b.getCode());
                dataRow.createCell(2).setCellValue(String.valueOf(b.getFromDate()));
                dataRow.createCell(3).setCellValue(String.valueOf(b.getToDate()));
                dataRow.createCell(4).setCellValue(b.getBookName());
                dataRow.createCell(5).setCellValue(b.getMemberName());
                dataRow.createCell(6).setCellValue(String.valueOf(b.getRentStatus()));
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            workbook.close();
            out.close();
        }
    }
}
