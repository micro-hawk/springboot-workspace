package com.springmailer.springmailer.service.impl;

import com.springmailer.springmailer.model.request.AddEmailSenderRequest;
import com.springmailer.springmailer.model.vo.AttachmentVo;
import com.springmailer.springmailer.service.api.EmailSenderService;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@Slf4j
public class EmailSenderServiceImpl implements EmailSenderService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String userMail;

    @Override
    public void sendMail(AddEmailSenderRequest request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(userMail);
        message.setTo(request.getToEmail());
        message.setSubject(request.getSubject());
        message.setText(request.getBody());

        javaMailSender.send(message);
        log.info("Mail successfully sent !");
    }

    @Override
    public void sendMailWithAttachment(AddEmailSenderRequest request) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setFrom(userMail);
        mimeMessageHelper.setSubject(request.getSubject());
        mimeMessageHelper.setText(request.getBody());

        FileSystemResource fileSystem = new FileSystemResource(request.getAttachment());

        mimeMessageHelper.addAttachment("FileNameMicroHawk", fileSystem);

        javaMailSender.send(mimeMessage);
        log.info("Mail with Attachment sent successfully !");
    }

    @Override
    public void sendEmailWithAttachments(AddEmailSenderRequest emailRequest) throws IOException {
        List<AttachmentVo> attachments = null;
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(emailRequest.getToEmail());
            helper.setSubject(emailRequest.getSubject());
            helper.setText(emailRequest.getBody());

            attachments = emailRequest.getAttachments();
            if (attachments != null && !attachments.isEmpty()) {
                for (AttachmentVo attachment : attachments) {
                    // Create a temporary file with the attachment bytes
                    String tempDir = System.getProperty("java.io.tmpdir");
                    File tempFile = File.createTempFile("temp", attachment.getFilename(), new File(tempDir));
                    Path tempFilePath = tempFile.toPath();
                    Files.write(tempFilePath, attachment.getContent());

                    // Attach the temporary file to the email
                    DataSource dataSource = new FileDataSource(tempFile);
                    helper.addAttachment(attachment.getFilename(), dataSource);
                }
            }

            javaMailSender.send(message);
            log.info("Mail with Attachment sent successfully !");
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
            // Handle exception
        } finally {
            // Delete the temporary files
            if (attachments != null && !attachments.isEmpty()) {
                for (AttachmentVo attachment : attachments) {
                    File tempFile = File.createTempFile("temp", attachment.getFilename());
                    tempFile.delete();
                }
            }
        }
    }

}
