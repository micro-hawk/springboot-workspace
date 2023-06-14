package com.springmailer.springmailer.service.api;

import com.springmailer.springmailer.model.dto.TemplateMailResponse;
import com.springmailer.springmailer.model.request.TemplateMailRequest;

public interface TemplateMailService {

    TemplateMailResponse sendEmail(TemplateMailRequest request);
}
