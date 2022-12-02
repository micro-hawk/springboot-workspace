package com.springmailer.springmailer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailSenderDto {
    private String toEmail;
    private String subject;
    private String body;
}
