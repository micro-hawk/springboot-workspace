package com.flexehr.whatsapp.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TextMessageTemplateVo {
    private boolean preview_url;
    private String body;
}
