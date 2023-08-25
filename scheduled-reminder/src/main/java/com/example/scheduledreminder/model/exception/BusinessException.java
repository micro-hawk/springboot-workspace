package com.example.scheduledreminder.model.exception;

import com.example.scheduledreminder.model.enums.ApiResponseStatus;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
public class BusinessException extends Exception {

    private ApiResponseStatus status;
    private String message;

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
