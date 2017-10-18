package com.beck.interceptor;

import com.beck.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RequestLogInterceptor implements HandlerInterceptor{
  private final Logger logger = LoggerFactory.getLogger(Application.class);
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    logger.info(String.format("[---->] url:%s, method:%s, token:%s", request.getRequestURI(), request.getMethod(), request.getHeader("token")));
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    logger.info(String.format("[<----] url:%s method:%s", request.getRequestURI(), request.getMethod()));
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

  }
}
