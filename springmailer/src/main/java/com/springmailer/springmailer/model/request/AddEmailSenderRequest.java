package com.springmailer.springmailer.model.request;

import com.springmailer.springmailer.model.vo.AttachmentVo;
import java.util.List;
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
    private List<AttachmentVo> attachments;
}
