package com.bootcamp.demo.infra.web;

public enum SysCode {
    OK("000000", "Success."), //
    FAIL("100000", "Fail."), //
    EXCEPTION("999999", "Unhandled Exception."), //
    ;
  
    private String code;
    private String message;
  
    private SysCode(String code, String message) {
      this.code = code;
      this.message = message;
    }
  
    public String getCode() {
      return this.code;
    }
  
    public String getMessage() {
      return this.message;
    }
  }