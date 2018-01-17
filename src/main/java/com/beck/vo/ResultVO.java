package com.beck.vo;

import lombok.Data;

@Data
public class ResultVO<T> {
    public Integer code;
    public String message;
    public T result;

    public ResultVO() {
    }

    public ResultVO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultVO(Integer code, String message, T result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

}
