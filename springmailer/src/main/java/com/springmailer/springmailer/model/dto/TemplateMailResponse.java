package com.springmailer.springmailer.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemplateMailResponse {

    private String message;
    private Boolean status;
}
