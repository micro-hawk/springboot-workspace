package com.springmailer.springmailer.service.impl;

import com.springmailer.springmailer.model.TemplateMailResponse;
import com.springmailer.springmailer.request.TemplateMailRequest;
import com.springmailer.springmailer.service.api.TemplateMailService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

@Service
@Slf4j
public class TemplateMailServiceImpl implements TemplateMailService {

    @Value("${spring.mail.username}")
    private String userMail;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private Configuration config;

    @Override
    public TemplateMailResponse sendEmail(TemplateMailRequest request) {
        Map<String, Object> model = new HashMap<>();
        model.put("name", request.getName());

        TemplateMailResponse response = new TemplateMailResponse();
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            // set mediaType
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
            // add attachment
//            helper.addAttachment("document.pdf", new ClassPathResource("xyz.pdf"));
            Template t = config.getTemplate("ehr_onboard.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
            helper.setTo(request.getToEmail());
            helper.setText(html, true);
            helper.setSubject(request.getSubject());
            helper.setFrom(userMail);
            javaMailSender.send(message);
            response.setMessage("mail send to : " + request.getToEmail());
            response.setStatus(Boolean.TRUE);
        } catch (MessagingException | IOException | TemplateException e) {
            response.setMessage("Mail Sending Failure : " + e.getMessage());
            response.setStatus(Boolean.FALSE);
        }
        return response;
    }
}
