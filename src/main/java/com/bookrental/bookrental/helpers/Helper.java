package com.bookrental.bookrental.helpers;

import com.bookrental.bookrental.model.BookTransaction;
import com.bookrental.bookrental.pojo.trasaction.BookTransactionResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Helper {

    public static String[] HEADERS = {"ID", "Code", "From Date", "To Date", "Book", "Member", "Rent Status"};
    public static String SHEET_NAME = "book-transaction";

    /*public static ByteArrayInputStream dataToExcel(List<BookTransactionResponse> list) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            Sheet sheet = workbook.createSheet(SHEET_NAME);
            sheet.setDefaultColumnWidth(20);
            Row row = sheet.createRow(0);
            for (int i = 0; i < HEADERS.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(HEADERS[i]);
            }
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
    }*/

    public static ByteArrayInputStream dataToExcel(List<?> list, String tableName, String[] columnNames) throws IOException {

        Workbook workbook = new XSSFWorkbook();

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            Sheet sheet = workbook.createSheet(tableName);

            sheet.setDefaultColumnWidth(20);

            Row row = sheet.createRow(0);

            for (int i = 0; i < columnNames.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(columnNames[i]);
            }

            int rowIndex = 1;

            for (Object object : list) {

                Row dataRow = sheet.createRow(rowIndex);
                rowIndex++;

                int columnIndex = 0;
                for (String columnName : columnNames) {
                    Cell cell = dataRow.createCell(columnIndex);

                    Object value = BeanUtils.getProperty(object, columnName);
                    cell.setCellValue(String.valueOf(value));
                    columnIndex++;
                }
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


    public static boolean checkExcelFormat(MultipartFile file) {
        String contentType = file.getContentType();

        if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            return true;
        } else {
            return false;
        }
    }

    // convert excel to list of transactions

    /*public static List<BookTransaction> convertExcelToListOfTransaction(InputStream is) {
        List<BookTransaction> list = new ArrayList<>();
        try {

            XSSFWorkbook workbook = new XSSFWorkbook(is);
            XSSFSheet sheet = workbook.getSheet("book-transaction");
            int rowNumber = 0;

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();

                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cells = row.iterator();
                int cid = 0;

                BookTransaction bookTransaction = new BookTransaction();

                while (cells.hasNext()) {
                    Cell cell = cells.next();

                    switch (cid) {
                        case 0:
                            bookTransaction.setId((int) cell.getNumericCellValue());
                            break;
                        case 1:
                            bookTransaction.setCode("2");
                            break;
                        case 2:
                            bookTransaction.setFromDate(null);
                            break;
                        case 3:
                            bookTransaction.setToDate(null);
                            break;
                        default:
                            break;
                    }
                    cid++;
                }
                list.add(bookTransaction);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }*/


    public static <T> List<T> convertExcelToList(Class<T> clazz, InputStream is) {
        List<T> list = new ArrayList<>();
        try {

            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheet("book-transaction");
            int rowNumber = 0;

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();

                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cells = row.iterator();
                int cid = 0;

                // Create a new instance of the class
                T object = clazz.newInstance();

                // Get all of the fields in the class
                Field[] fields = clazz.getDeclaredFields();

                while (cells.hasNext()) {
                    Cell cell = cells.next();

                    // Get the field at the current index
                    Field field = fields[cid];

                    // Set the value of the field
                    field.setAccessible(true);
                    switch (field.getType().getSimpleName()) {
                        case "int":
                            field.setInt(object, (int) cell.getNumericCellValue());
                            break;
                        case "double":
                            field.setDouble(object, cell.getNumericCellValue());
                            break;
                        case "String":
                            field.set(object, cell.getStringCellValue());
                            break;
                        case "Date":
                            field.set(object, cell.getDateCellValue());
                            break;
                        default:
                            break;
                    }

                    cid++;
                }
                list.add(object);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
