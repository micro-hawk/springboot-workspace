package com.poc.pdfgenerator.controller;

import com.poc.pdfgenerator.model.dto.CityDto;
import com.poc.pdfgenerator.service.api.CityService;
import com.poc.pdfgenerator.utils.GeneratePdfReportHelper;
import java.io.ByteArrayInputStream;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;

@RestController
@Slf4j
public class PdfReportController {

    @Autowired
    private CityService cityService;

    @RequestMapping(value = "/pdfreport", method = RequestMethod.GET,
        produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> citiesReport() {

        List<CityDto> cities = (List<CityDto>) cityService.getAllCities();

        ByteArrayInputStream bis = GeneratePdfReportHelper.citiesReport(cities);

        var headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");

        return ResponseEntity
            .ok()
            .headers(headers)
            .contentType(MediaType.APPLICATION_PDF)
            .body(new InputStreamResource(bis));
    }
}
