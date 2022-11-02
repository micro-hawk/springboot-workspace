package com.microhawk.webissshdemo.exception;

import com.microhawk.webissshdemo.model.exception.BusinessException;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

@Slf4j
@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        Map<String, Object> map = super.getErrorAttributes(
            request, options);
        if (getError(request) instanceof BusinessException) {
            BusinessException ex = (BusinessException) getError(request);
            log.error("Business Exception with message: {} and status: {}", ex.getMessage(), ex.getStatus());
            map.put("status", "OK");
            map.put("message", ex.getMessage());
            map.put("responseCode", ex.getStatus());
            return map;
        }
        Exception ex = (Exception) getError(request);
        log.error("Unspecified Exception occurred with message: {} and cause: {}", ex.getMessage(), ex);
        map.put("status", "FAIL");
        map.put("message", "UNSPECIFIED");
        map.put("responseCode", "1500");
        return map;
    }
}
