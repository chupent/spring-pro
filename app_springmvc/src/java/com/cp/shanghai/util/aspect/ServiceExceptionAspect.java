package com.cp.shanghai.util.aspect;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName ServiceExceptionAspect
 * @Description TODO service层的异常信息进行统一捕获输出日志
 * @createdate 2018/9/26 星期三 16:29
 */
@Aspect
public class ServiceExceptionAspect {

    //对service层的方法进行切面
    private static final String EXECUTION = "execution(* com.cp.shanghai.service..*.*(..))";
    private final Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @AfterThrowing(value = EXECUTION, throwing = "e")
    public void AspectException(JoinPoint joinPoint, Throwable e) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n===============================================================================================\n");
        sb.append("\n异常类:" + e.toString());
        sb.append("\n出现异常的目标类:" + joinPoint.getTarget().getClass().getName());
        sb.append("\n出现异常的目标类的方法:" + joinPoint.getSignature().getName());
        sb.append("\n出现异常的目标类的方法的入参:" + new Gson().toJson(joinPoint.getArgs()));
        sb.append("\n\n===============================================================================================\n");
        logger.info(sb.toString());
    }
}
