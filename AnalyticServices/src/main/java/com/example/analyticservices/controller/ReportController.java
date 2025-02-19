package com.example.analyticservices.controller;

import com.example.analyticservices.service.ReportService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadExcelReport(@RequestParam LocalDate startDate,
                                                      @RequestParam LocalDate endDate
    ) throws IOException {
        try {
            List<Map<String, Object>> orders = reportService.getOrderReport(startDate, endDate); // Query tanpa JPA

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Orders Report");

            // Buat Header
            Row headerRow = sheet.createRow(0);
            String[] headers = {
                    "Order ID", "Order Status", "Order Date", "Order Price",
                    "Product Name", "SKU Product", "Product Price", "Quantity",
                    "Customer Name", "Email User", "Customer Address", "Regency",
                    "Postal Code", "Province"
            };

            for (int i = 0; i < headers.length; i++) {
                headerRow.createCell(i).setCellValue(headers[i]);
            }

            // Isi Data dari Database
            int rowNum = 1;
            for (Map<String, Object> order : orders) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(order.get("Order ID").toString());
                row.createCell(1).setCellValue(order.get("Order Status").toString());
                row.createCell(2).setCellValue(order.get("Order Date").toString());
                row.createCell(3).setCellValue(order.get("Order Price").toString());
                row.createCell(4).setCellValue(order.get("Product Name").toString());
                row.createCell(5).setCellValue(order.get("SKU Product").toString());
                row.createCell(6).setCellValue(order.get("Product Price").toString());
                row.createCell(7).setCellValue(order.get("Quantity").toString());
                row.createCell(8).setCellValue(order.get("Customer Name").toString());
                row.createCell(9).setCellValue(order.get("Email User").toString());
                row.createCell(10).setCellValue(order.get("Customer Address").toString());
                row.createCell(11).setCellValue(order.get("Regency").toString());
                row.createCell(12).setCellValue(order.get("Postal Code").toString());
                row.createCell(13).setCellValue(order.get("Province").toString());
            }

            // Simpan ke OutputStream
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            workbook.close();

            byte[] excelBytes = outputStream.toByteArray();
            outputStream.close();


            LocalDateTime now = LocalDateTime.now();
            String filename = "report_" + startDate.format(DateTimeFormatter.ofPattern("yyyyMMdd")) + "-" + endDate.format(DateTimeFormatter.ofPattern("yyyyMMdd")) + "_" + now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")) + ".xlsx";
            // Kirim sebagai response dengan header untuk download
            HttpHeaders headersExcel = new HttpHeaders();
            headersExcel.add("Content-Disposition", "attachment; filename=" + filename);
            return new ResponseEntity<>(excelBytes, headersExcel, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}