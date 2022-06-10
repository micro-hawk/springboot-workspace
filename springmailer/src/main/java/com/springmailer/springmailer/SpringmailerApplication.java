package com.springmailer.springmailer;

import com.springmailer.springmailer.service.api.EmailSenderService;
import com.springmailer.springmailer.service.impl.EmailSenderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import javax.mail.MessagingException;

@SpringBootApplication
public class SpringmailerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringmailerApplication.class, args);
    }

//    @Autowired
//    private EmailSenderServiceImpl service;
//
//    @EventListener(ApplicationReadyEvent.class)
//    public void triggerMail() throws MessagingException {
//        service.sendMailWithAttachment("microhawkx@gmail.com",
//                "GANDU KAISI HAI ?",
//                "CHUITYUA",
//                "/home/microhawk/Downloads/Vimix-1080p.tar.xz");
//    }
}
