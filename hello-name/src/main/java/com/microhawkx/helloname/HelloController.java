package com.microhawkx.helloname;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/hello")
public class HelloController {
    @GetMapping("/{name}")
    String hello(@PathVariable("name") String name) {
        return "Hello, " +name+ ", This is Hello Service !";
    }
}