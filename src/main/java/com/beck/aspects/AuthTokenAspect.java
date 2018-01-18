package com.beck.aspects;

import com.beck.enums.UrlsEnum;
import com.beck.enums.ExceptionEnum;
import com.beck.vo.ResultVO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Order(2)
public class AuthTokenAspect {
    @Pointcut("execution(* com.beck.controller..*(..))")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();

        String webToken = request.getHeader("token");
        Boolean flag = UrlsEnum.ALLOW_TOKEN_URLS.getUrls().contains(request.getMethod() + request.getRequestURI());
        if (!flag) {
            if (webToken != null) {
                Object tokenValue = request.getSession().getAttribute(request.getHeader("token"));
                if (tokenValue == null) {
                    return new ResultVO<>(400, "Authorize Fail!", ExceptionEnum.TOKEN_ERROR);
                }
            } else {
                return new ResultVO<>(400, "Authorize Fail!", ExceptionEnum.TOKEN_ERROR);
            }
        }

        return pjp.proceed();
    }
}
