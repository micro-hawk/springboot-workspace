package com.springmailer.springmailer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailSenderDto {
    private String toEmail;
    private String subject;
    private String body;
}
