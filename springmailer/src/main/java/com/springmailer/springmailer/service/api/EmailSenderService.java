package com.springmailer.springmailer.service.api;

import com.springmailer.springmailer.request.AddEmailSenderRequest;

import javax.mail.MessagingException;

public interface EmailSenderService {
    public void sendMail(AddEmailSenderRequest request);

    public void sendMailWithAttachment(AddEmailSenderRequest request) throws MessagingException;
}
