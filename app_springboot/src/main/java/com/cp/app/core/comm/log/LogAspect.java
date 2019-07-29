package com.cp.app.core.comm.log;

import com.cp.app.core.comm.uitl.SystemUtil;
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
 * @ClassName Log
 * @Description TODO aop方式将日志分离
 * @createdate 2019/7/24 星期三 10:41
 */
@Aspect
@Component
public class LogAspect {

    private Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution(public * com.cp.app.core.web.*.*.*(..)) && !execution(public * com.cp.app.core.web.*.*.*getQRCode(..))")
    public void log(){
        logger.debug("begin");
    }
    @Before("log()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        logger.info("前置切面after……");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpRequest = attributes.getRequest();
        long userid=SystemUtil.currentUser().getUserId();
        logger.info("USERID",userid);
        logger.info("API",httpRequest.getContextPath().toString());
        logger.info("URL : " + httpRequest.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + httpRequest.getMethod());
        logger.info("IP : " + httpRequest.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }
    @After("log()")
    public void after() {
        logger.info("后置切面after……");
    }
    @AfterReturning(returning = "ret",pointcut = "log()")
    public void doAfterReturning(Object ret){
        // 处理完请求，返回内容
        logger.info("RESPONSE : " + ret);
    }
}
