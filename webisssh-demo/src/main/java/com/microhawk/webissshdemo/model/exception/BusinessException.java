package com.microhawk.webissshdemo.model.exception;

import com.microhawk.webissshdemo.model.ApiResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.micrometer.core.instrument.util.StringUtils;

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
