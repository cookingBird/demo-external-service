package com.example.demoexternalservice.commons.error;

import com.example.demoexternalservice.commons.error.api.ErrorType;

public enum CommonErrorType implements ErrorType {

    // 1xxxx：系统异常
    SYSTEM_ERROR(10000, "系统异常，请稍后再试"),
    SERVICE_UNAVAILABLE(10001, "服务不可用"),
    DATABASE_ERROR(10002, "数据库异常"),

    // 2xxxx：参数错误
    PARAM_MISSING(20001, "参数缺失"),
    PARAM_INVALID(20002, "参数无效"),

    // 3xxxx：业务错误
    USER_NOT_FOUND(30001, "用户不存在"),
    DUPLICATE_OPERATION(30002, "重复操作"),

    // 4xxxx：权限相关
    UNAUTHORIZED(40001, "未登录或登录已过期"),
    FORBIDDEN(40003, "无权限访问");

    private final int code;
    private final String message;

    CommonErrorType(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() { return code; }

    public String getMessage() { return message; }
}
