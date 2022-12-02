package com.springmailer.springmailer.service.api;

import com.springmailer.springmailer.model.TemplateMailResponse;
import com.springmailer.springmailer.request.TemplateMailRequest;

public interface TemplateMailService {

    TemplateMailResponse sendEmail(TemplateMailRequest request);
}
