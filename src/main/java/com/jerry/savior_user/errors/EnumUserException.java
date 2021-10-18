package com.jerry.savior_user.errors;

import com.jerry.savior_common.asserts.BusinessExceptionAssert;

/**
 * @author 22454
 */
public enum EnumUserException implements BusinessExceptionAssert {
    /**
     * 用户不存在
     */
    USER_NOT_FOUND(300001, "用户不存在"),
    HAS_VERIFIED(300002, "您已完成实名认证");
    private final Integer code;
    private final String message;

    EnumUserException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
