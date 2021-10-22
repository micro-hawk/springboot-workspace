package com.demo.getAPI.controller;

import com.demo.getAPI.service.mainService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/v1")
public class mainController {

    @GetMapping(value = "/api")
    public List<mainService> getAllDetails() {
        return Arrays.asList(
                new mainService("3854s", "John", "fuck", "kjhskdh@fkl.com"),
                new mainService("8756xdc", "kamina", "okok", "kjhskdh@fkl.com"),
                new mainService("sfc2", "loda", "fuckin", "kjhskdh@fkl.com"),
                new mainService("25rfed", "bc", "duck", "kjhskdh@fkl.com"),
                new mainService("46ygdcv", "gandu", "lmaoi", "kjhskdh@fkl.com")
        );
    }

}
