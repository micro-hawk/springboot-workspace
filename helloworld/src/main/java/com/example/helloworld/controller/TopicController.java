package com.example.helloworld.controller;

import com.example.helloworld.Topic;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/topic")
public class TopicController {

    @GetMapping(value = "/all")
    public List<Topic> getAllTopics() {
        return Arrays.asList(
                new Topic("spring", "SpringBoot framework", "SpringBoot description"),
                new Topic("javascript", "javascript", "javascript description"),
                new Topic("nodejs", "nodejs", "express")
        );
    }
}
