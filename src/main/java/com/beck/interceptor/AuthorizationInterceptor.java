package com.beck.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.beck.libs.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

  private static final Logger logger = LoggerFactory.getLogger(AuthorizationInterceptor.class);
  private final String[] notAuthorizePath = {"POST/login", "POST/register"};

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    boolean isAuthorize = false;
    logger.info(String.format("[---->] url:%s, method:%s, token:%s", request.getRequestURI(), request.getMethod(), request.getHeader("token")));
    if (!request.getRequestURI().equals("/error")){
      if (!Arrays.asList(notAuthorizePath).contains(request.getMethod() + request.getRequestURI())) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        ServletOutputStream outputStream = response.getOutputStream();
        if (request.getHeader("token") != null) {
          logger.info(request.getHeader("token"));
          Object tokenValue = request.getSession().getAttribute(request.getHeader("token"));
          if (tokenValue == null) {
            new WrapMethod().wrapFail(request, outputStream);
          } else {
            isAuthorize = true;
          }
        } else {
          new WrapMethod().wrapFail(request, outputStream);
        }
      } else {
        isAuthorize = true;
      }
    }else {
      isAuthorize = true;
    }
    return isAuthorize;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    logger.info(String.format("[<----] url:%s method:%s", request.getRequestURI(), request.getMethod()));
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

  }
}

class WrapMethod {
  private static final Logger logger = LoggerFactory.getLogger(AuthorizationInterceptor.class);

  void wrapFail(HttpServletRequest request, ServletOutputStream outputStream) throws IOException {
    String jsonObject = JSONObject.toJSONString(new ResponseData(400, "Authorize Fail", null));
    outputStream.print(jsonObject);
    outputStream.flush();
    outputStream.close();
    logger.info(String.format("[<----] url:%s method:%s", request.getRequestURI(), request.getMethod()));
  }
}
