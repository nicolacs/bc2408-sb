package com.bootcamp.bc_forum.util;

import java.util.ArrayList;
import java.util.List;
import com.bootcamp.bc_forum.model.dto.UserDTO;

public class ApiResp<T> {
    private String code;
    private String message;
    private List<T> data; //可以 private T data

    //自己寫唔用lombok
    //有Generic 既version
    // inner class can call outer, outer cannot call inner
    public String getCode(){
        return this.code;
    }

    public String getMessage(){
        return this.message;
    }

    public List<T> getData(){
        return this.data;
    }

    public ApiResp(Builder<T> builder) {
        this.code = builder.code;
        this.message = builder.message;
        this.data = builder.data;
    }

    public static <U> Builder<U> builder() {
        return new Builder<>();
    }

    public static class Builder<T>{
        private String code;
        private String message;
        private List<T> data;

        public Builder<T> code (String code){
            this.code = code;
            return this;
        }

        public Builder<T> message(String message) {
            this.message = message;
            return this;
        }

        public Builder<T> data(List<T> data) {
            this.data = data;
            return this;
          }

        public ApiResp<T> build() {
            return new ApiResp<>(this);
        }
    }

    public static void main(String[] args) {
    // !
    ApiResp<UserDTO> response = ApiResp.<UserDTO>builder() // 要定義builder個<U>, here <UserDTO>
        .code("000000") //
        .message("Success.") //
        .build();

    // Controller Layer -> return ApiResp<>
    // Global Exception Handler -> return ApiResp<>

    //EX3 Suggestion: 
    // enum SysCode for Api Response use.
    // enum ErrorCode for error only

    List<String> strings = new ArrayList<>(); // after Java 1.5
  }
}
