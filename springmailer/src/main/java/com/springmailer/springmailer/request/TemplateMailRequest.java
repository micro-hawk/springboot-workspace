package com.springmailer.springmailer.request;

import lombok.Data;

@Data
public class TemplateMailRequest {

    private String name;
    private String toEmail;
    private String fromEmail;
    private String subject;
}
