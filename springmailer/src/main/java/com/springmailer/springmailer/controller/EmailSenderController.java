package com.springmailer.springmailer.controller;

import com.springmailer.springmailer.model.request.AddEmailSenderRequest;
import com.springmailer.springmailer.service.api.EmailSenderService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
public class EmailSenderController {

    @Autowired
    private EmailSenderService emailSenderService;

    @PostMapping(value = "/mail")
    public void sendMail(@RequestBody AddEmailSenderRequest request) {
        emailSenderService.sendMail(request);
    }

    @PostMapping(value = "/mail-add-attachment")
    public void sendMailWithAttachment(@RequestBody AddEmailSenderRequest request) throws MessagingException {
        emailSenderService.sendMailWithAttachment(request);
    }

    @PostMapping(value = "/mail-with-multi-attachments")
    public void sendEmailWithAttachments(@RequestBody AddEmailSenderRequest emailRequest) throws IOException {
        emailSenderService.sendEmailWithAttachments(emailRequest);
    }
}
