package com.flexehr.whatsapp.model.exception;

import com.flexehr.whatsapp.model.ApiResponseStatus;
import io.micrometer.core.instrument.util.StringUtils;

public class BusinessException extends Exception {

    private final ApiResponseStatus status;
    private final String message;

    public BusinessException(ApiResponseStatus status) {
        this.status = status;
        this.status.setCode(status.getCode());
        this.message = status.getMessage();
    }

    @Override
    public String getMessage() {
        if (StringUtils.isNotBlank(message)) {
            return this.message;
        } else {
            return this.status.getMessage();
        }
    }

    public int getStatus() {
        return this.status.getCode();
    }
}
