package com.beck.exception;

import com.beck.enums.ExceptionEnum;
import lombok.Getter;

@Getter
public class ProjectException extends RuntimeException {
    private static final long serialVersionUID = -8008542509443641783L;
    private Integer code;

    public ProjectException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMessage());
        this.code = exceptionEnum.getCode();
    }
}
