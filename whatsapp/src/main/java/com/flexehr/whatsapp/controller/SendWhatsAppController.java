package com.flexehr.whatsapp.controller;

import com.flexehr.whatsapp.client.FacebookWebClient;
import com.flexehr.whatsapp.service.WhatsAppSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class SendWhatsAppController {

    @Autowired
    private WhatsAppSender whatsAppSender;
    @Autowired
    private FacebookWebClient webClient;

    @PostMapping(value = "/send")
    public Mono<String> sendMessage() {
        return webClient.sendHelloMessage();

    }
}
