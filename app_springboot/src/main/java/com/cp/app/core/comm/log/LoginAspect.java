package com.cp.app.core.comm.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName LoginAspect
 * @Description TODO
 * @createdate 2019/7/24 星期三 11:17
 */
@Aspect
@Component
public class LoginAspect {
    private Logger logger = LoggerFactory.getLogger(LoginAspect.class);
    @Pointcut("execution(public * com.cp.app.core.comm.security.handler.SystemAuthenticationSuccessHandler.onAuthenticationSuccess(..))")
    public void pointcut(){
        logger.debug("begin");
    }
    @Before("pointcut()")
    public void before(JoinPoint joinPoint) throws Throwable{
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpRequest = attributes.getRequest();
        logger.info("URL : " + httpRequest.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + httpRequest.getMethod());
        logger.info("IP : " + httpRequest.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }
    @After("pointcut()")
    public void after() {
        logger.info("后置切面after……");
    }
    @AfterReturning(returning = "ret",pointcut = "pointcut()")
    public void doAfterReturning(Object ret){
        // 处理完请求，返回内容
        logger.info("RESPONSE : " + ret);
    }
}
