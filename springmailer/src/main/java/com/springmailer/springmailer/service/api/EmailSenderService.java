package com.springmailer.springmailer.service.api;

import com.springmailer.springmailer.request.AddEmailSenderRequest;

import javax.mail.MessagingException;

public interface EmailSenderService {

    void sendMail(AddEmailSenderRequest request);

    void sendMailWithAttachment(AddEmailSenderRequest request) throws MessagingException;
}
