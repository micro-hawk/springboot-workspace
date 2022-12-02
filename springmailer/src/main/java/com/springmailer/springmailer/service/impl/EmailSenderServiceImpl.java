package com.springmailer.springmailer.service.impl;

import com.springmailer.springmailer.request.AddEmailSenderRequest;
import com.springmailer.springmailer.service.api.EmailSenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@Slf4j
public class EmailSenderServiceImpl implements EmailSenderService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String userMail;

    @Override
    public void sendMail(AddEmailSenderRequest request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(userMail);
        message.setTo(request.getToEmail());
        message.setSubject(request.getSubject());
        message.setText(request.getBody());

        javaMailSender.send(message);
        log.info("Mail successfully sent !");
    }

    @Override
    public void sendMailWithAttachment(AddEmailSenderRequest request) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setFrom(userMail);
        mimeMessageHelper.setSubject(request.getSubject());
        mimeMessageHelper.setText(request.getBody());

        FileSystemResource fileSystem = new FileSystemResource(request.getAttachment());

        mimeMessageHelper.addAttachment("FileNameMicroHawk", fileSystem);

        javaMailSender.send(mimeMessage);
        log.info("Mail with Attachment sent successfully !");
    }
}
