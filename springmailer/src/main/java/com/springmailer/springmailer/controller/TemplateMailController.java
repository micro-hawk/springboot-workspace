package com.springmailer.springmailer.controller;

import com.springmailer.springmailer.model.TemplateMailResponse;
import com.springmailer.springmailer.request.TemplateMailRequest;
import com.springmailer.springmailer.service.api.TemplateMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TemplateMailController {

    @Autowired
    private TemplateMailService mailService;

    @PostMapping("/sendingEmail")
    public TemplateMailResponse sendEmail(@RequestBody TemplateMailRequest request) {
        return mailService.sendEmail(request);
    }
}
