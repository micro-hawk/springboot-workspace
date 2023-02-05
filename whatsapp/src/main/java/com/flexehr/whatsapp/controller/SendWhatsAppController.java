package com.flexehr.whatsapp.controller;

import com.flexehr.whatsapp.model.request.WhatsAppSenderRequest;
import com.flexehr.whatsapp.service.WhatsAppSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendWhatsAppController {
    @Autowired
    private WhatsAppSender whatsAppSender;

    @PostMapping(value = "/send")
    public void sendMessage(@RequestBody WhatsAppSenderRequest request) throws Exception {
        whatsAppSender.sendWhatsAppMessage(request);
    }
}
