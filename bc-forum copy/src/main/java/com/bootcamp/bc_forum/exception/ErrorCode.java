package com.bootcamp.bc_forum.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    USER_NOT_FOUND(1, "User not found."), //
    INVAILD_INPUT(2, "Invalid Input."), //
    RESTEMPLATE_ERROR_JSONPLACEHOLDER(3, "ResTemplate Error - JsonPlaceHolder");

    private Integer code;
    private String msg;

    private ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
      }

 
}
