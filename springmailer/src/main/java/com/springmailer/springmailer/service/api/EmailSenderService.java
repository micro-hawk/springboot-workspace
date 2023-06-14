package com.springmailer.springmailer.service.api;

import com.springmailer.springmailer.model.request.AddEmailSenderRequest;

import java.io.IOException;
import javax.mail.MessagingException;

public interface EmailSenderService {

    void sendMail(AddEmailSenderRequest request);

    void sendMailWithAttachment(AddEmailSenderRequest request) throws MessagingException;

    void sendEmailWithAttachments(AddEmailSenderRequest emailRequest) throws IOException;
}
