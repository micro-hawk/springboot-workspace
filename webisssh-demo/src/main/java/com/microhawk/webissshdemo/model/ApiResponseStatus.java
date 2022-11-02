package com.microhawk.webissshdemo.model;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum ApiResponseStatus {
    SUCCESS(1000, "Success"),
    ERROR_IN_REGISTRATION(1001, "Error In Registration"),
    MAIL_ALREADY_EXIST(1002, "E-mail already exists"),
    DATA_INVALID(1003, "Invalid Data"),
    INVALID_REFRESH_TOKEN(1010, "Invalid Refresh Token"),
    FAILURE(1011, "Failure"),
    ERROR_IN_LOGIN(1012, "Error in Login"),
    PASSWORD_VALIDATION_FAILED(1013, "Password Validation Failed"),
    USER_NOT_REGISTERED(1014, "User Not registered"),
    PASSWORD_NOT_SET(1015, "Password Not Set"),
    INVALID_MAIL_ID(1016, "Invalid Email-id"),
    INVALID_PHONE_NO(1017, "Invalid Phone No"),
    INVALID_USERNAME(1018, "Invalid Username a-z, 0-9, _, -, it must have 3-15 character"),
    PHONE_NO_EXIST(1019, "Phone No Exist"),
    USERNAME_NO(1020, "Username Already Exist"),
    USER_NOT_ENABLED(1021, "User not enabled"),
    INVALID_CREDENTIALS(1022, "Invalid Credentials"),
    RESET_TOKEN_INVALID(1023, "Password ResetToken is Invalid"),
    RESET_TOKEN_EXPIRED(1024, "Password ResetToken has Expired"),
    INVALID_ACCESS_TOKEN(1025, "INVALID_ACCESS_TOKEN");
    private static final Map<String, ApiResponseStatus> ENUM_MAP;

    static {
        Map<String, ApiResponseStatus> map = new ConcurrentHashMap<String, ApiResponseStatus>();
        for (ApiResponseStatus instance : ApiResponseStatus.values()) {
            map.put(String.valueOf(instance.getCode()), instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    private int code;
    private String message;

    ApiResponseStatus(int status, String message) {
        this.code = status;
        this.message = message;
    }

    public static ApiResponseStatus getInstance(String code) {
        return ENUM_MAP.get(code);
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    }
