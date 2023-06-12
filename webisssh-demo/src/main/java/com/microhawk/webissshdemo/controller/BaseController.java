package com.microhawk.webissshdemo.controller;

import com.microhawk.webissshdemo.model.response.GlobalResponse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BaseController {

    public <T> GlobalResponse<T> toGlobalResponse(T data) {
        return new GlobalResponse<>(data);
    }
}
