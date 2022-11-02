package com.poc.pdfgenerator.service.impl;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.poc.pdfgenerator.service.api.CreatePdfFileService;
import java.io.FileNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CreatePdfFileServiceImpl implements CreatePdfFileService {

    @Override
    public void createPdf() {
        String filePath = "/home/microhawk/Desktop/pdfs-poc/SampleTest.pdf";
        try {
            PdfWriter writer = new PdfWriter(filePath);
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument);
            document.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
