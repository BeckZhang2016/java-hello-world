package com.beck.controller;

import com.beck.vo.ResultVO;
import com.sun.org.apache.bcel.internal.generic.FADD;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ErrorViewResolver;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
public class ErrorController extends AbstractErrorController {
    private Logger logger = LoggerFactory.getLogger(ErrorController.class);

    public ErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @RequestMapping("/error")
    public ModelAndView getErrorPath(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = Collections.unmodifiableMap(getErrorAttributes(request, false));
        Throwable cause = getCause(request);
        int status = (Integer) model.get("status");
        String message = (String) model.get("message");
        String errorMessage = getErrorMessage(cause);
        logger.info(status + "," + message, cause);


        response.setStatus(status);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/error");
        return modelAndView;

    }

    @Override
    public String getErrorPath() {
        return null;
    }

    private Throwable getCause(HttpServletRequest request) {
        Throwable error = (Throwable) request.getAttribute("java.servlet.error.exception");
        if (error != null) {
            while (error instanceof ServletException && error.getCause() != null) {
                error = error.getCause();
            }
        }
        return error;
    }

    private String getErrorMessage(Throwable ex) {
        return "服务器错误，请联系管理员";
    }

    private boolean isJsonRequest(HttpServletRequest request) {
        String requstUri = request.getRequestURI();
        if (requstUri != null && requstUri.endsWith(".json")) {
            return true;
        } else {
            return false;
        }
    }
}
