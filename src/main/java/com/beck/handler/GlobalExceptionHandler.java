package com.beck.handler;

import com.beck.exception.ProjectException;
import com.beck.vo.ResultVO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody
@Component
public class GlobalExceptionHandler {
    /**
     * 404 project exception
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ProjectException.class})
    public ResultVO handleHttpMessageNotReadableException(ProjectException e) {
        return new ResultVO(e.getCode(), e.getMessage());
    }
}
