package com.beck.enums;

import lombok.Getter;

@Getter
public enum ExceptionEnum {

    TOKEN_ERROR(10, "Error token");

    private Integer code;
    private String message;

    ExceptionEnum(Integer code, String msg){
        this.code = code;
        this.message = msg;
    }
}
