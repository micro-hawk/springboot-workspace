package com.springmailer.springmailer.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddEmailSenderRequest {
    private String toEmail;
    private String subject;
    private String body;
    private String attachment;
}
