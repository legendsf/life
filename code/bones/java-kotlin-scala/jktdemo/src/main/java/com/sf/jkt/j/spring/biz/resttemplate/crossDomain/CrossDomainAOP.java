package com.sf.jkt.j.spring.biz.resttemplate.crossDomain;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Optional;

/**
 * @author nyc
 * @Date 2019/9/9
 */
@Component
@Aspect
@Slf4j
public class CrossDomainAOP {

  @Resource
  private HttpServletRequest request;
  @Value("${lite.host}")
  private String liteHost;


//  @Around("execution(*controller..*.*(..))")
  public Object around(ProceedingJoinPoint pjp) throws Throwable {
    //Object[] args = pjp.getArgs();
    //String declaringTypeName = signature.getDeclaringTypeName();
    //String join = Joiner.on(",").skipNulls().join(args);
    HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder
        .getRequestAttributes()).getResponse();
    response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
    response.setHeader("Access-Control-Allow-Credentials", "true");
    response.setHeader("Access-Control-Allow-Origin", "*");
    Signature signature = pjp.getSignature();
    String name = signature.getName();
    setVersion(pjp, name);
    String declaringTypeName = Optional.ofNullable(signature.getDeclaringTypeName()).orElse("");
    String[] split = declaringTypeName.split("\\.");
    if (ArrayUtils.isNotEmpty(split)) {
      String controllerName = split[split.length - 1];
      if ("MatchLotteryController".equals(controllerName)) {
        response.setHeader("Access-Control-Allow-Origin", liteHost);
      }
    }
    //log.info("invoke method {}, param:{}", declaringTypeName + name, join);
    return pjp.proceed();
  }

  private void setVersion(ProceedingJoinPoint pjp, String methodName) throws NoSuchMethodException {
    Class<?> aClass = pjp.getTarget().getClass();
    Class[] parameterTypes = ((MethodSignature) pjp.getSignature()).getParameterTypes();
    Method method = aClass.getMethod(methodName, parameterTypes);
//    VersionControl annotation = method.getAnnotation(VersionControl.class);
//    if (Objects.isNull(annotation)) {
//      return;
//    }
//    String v = request.getParameter("V");
//    ThreadLocalUtil.set(v);
  }
}
