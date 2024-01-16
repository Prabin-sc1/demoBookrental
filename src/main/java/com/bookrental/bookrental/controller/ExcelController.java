package com.bookrental.bookrental.controller;

import com.bookrental.bookrental.service.excel.ExcelService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.springframework.core.io.Resource;

@RestController
@RequestMapping("/excel")
public class ExcelController {
    private final ExcelService excelService;

    public ExcelController(ExcelService excelService) {
        this.excelService = excelService;
    }

//    @RequestMapping("/excell")
    @GetMapping("/excell")
    public ResponseEntity<Resource> download() throws IOException {
        String fileName = "booktransaction.xlsx";
        ByteArrayInputStream bis = excelService.getActualDataData();
        InputStreamResource file = new InputStreamResource(bis);

        ResponseEntity<Resource> body = ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
        return body;
    }
}
