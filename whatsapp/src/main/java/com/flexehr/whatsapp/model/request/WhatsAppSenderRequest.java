package com.flexehr.whatsapp.model.request;

import com.flexehr.whatsapp.model.vo.TextMessageTemplateVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WhatsAppSenderRequest {
    private String messaging_product;
    private String to;
    private String type;
    private TextMessageTemplateVo text;
}
