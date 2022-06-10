package com.springmailer.springmailer.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddEmailSenderRequest {
    private String toEmail;
    private String subject;
    private String body;
    private String attachment;
}
