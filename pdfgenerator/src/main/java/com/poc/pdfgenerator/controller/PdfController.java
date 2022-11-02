package com.poc.pdfgenerator.controller;

import com.poc.pdfgenerator.service.api.CreatePdfFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PdfController {

    @Autowired
    private CreatePdfFileService pdfFileService;

    @GetMapping(value = "/pdf-file")
    public String getPdf() {
        return "sample";
    }

    @GetMapping(value = "/create-pdf")
    public String createPdfFile() {
        pdfFileService.createPdf();
        return "redirect:/pdf-file";
    }
}
