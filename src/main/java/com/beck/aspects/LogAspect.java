package com.beck.aspects;

import com.alibaba.fastjson.JSON;
import com.beck.dto.RequestDTO;
import com.beck.dto.ResponseDTO;
import com.beck.vo.ResultVO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Aspect
@Component
@Order(1)
public class LogAspect {
    private Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution(* com.beck.controller..*(..))")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object interceptToken(ProceedingJoinPoint pjp) throws Throwable {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();

        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setRequestUri(request.getRequestURI());
        requestDTO.setMethod(request.getMethod());

        List<Object> params = new ArrayList<>();
        for (Object obj : pjp.getArgs()) {
            if (!(obj instanceof ServletRequest || obj instanceof ServletResponse)) {
                params.add(obj);
            }
        }

        requestDTO.setRequestArgs(params.toArray());

        logger.info(String.format("[---->] %s", JSON.toJSONString(requestDTO)));

        ResultVO resultVO = (ResultVO) pjp.proceed();
        if (resultVO != null) {
            HttpServletResponse response = ((ServletRequestAttributes) requestAttributes).getResponse();
            String statusCode = "200";
            if(resultVO.getCode() != Integer.parseInt(statusCode)){
                response.setStatus(400);
            }

            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMethod(request.getMethod());
            responseDTO.setRequestUrl(request.getRequestURI());
            responseDTO.setStatusCode(response.getStatus());
            responseDTO.setResponseArgs(resultVO);

            logger.info(String.format("[<----] %s", JSON.toJSONString(responseDTO)));

        }
        return resultVO;
    }


}
