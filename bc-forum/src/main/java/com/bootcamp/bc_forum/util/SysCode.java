package com.bootcamp.bc_forum.util;

import lombok.Getter;

@Getter
public enum SysCode {
    SUCCESS("000000", "Success."),
    FAIL("999999","Fail."),
    DATABASE_CONNECTION_FAIL("999997", "Database connection fail.")
    ;

    private String code;
    private String message;

    private SysCode(String code, String message){
        this.code = code;
        this.message = message;
    }

}
