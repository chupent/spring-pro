package com.cp.shanghai.util.aspect;


import com.cp.shanghai.pojo.ApiResponse;
import com.cp.shanghai.util.exception.BussinessException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.*;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;


/**
 * 1、导入AspectJ类库
 * 2、spring配置文件中实现Aop支持
 * 3、编写切面类，用@Aspectj注解标注
 *
 * @author chupengtang
 * @createtime 2018/9/19 星期三 11:14
 */
@Aspect
public class LogAspect {
    private static final String EXECUTION = "execution(* com.cp.shanghai.controller..*.*(..))";
    private final Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
    private final Gson gson = new GsonBuilder().setPrettyPrinting().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    private long startTimeMillis = 0; // 开始时间
    private long endTimeMillis = 0; // 结束时间
    private String requestURI;//请求地址
    private Map<?, ?> inputParamMap = null; // 传入参数
    private Map<String, Object> outputParamMap = null; // 存放输出结果

    /**
     * @return void
     * @MethodName doBeforeInServiceLayer
     * @Description 方法调用前触发，记录开始时间(前置通知)
     * @Author chupengtang
     * @CreateDate 2018/9/19 12:10
     * @Param [joinPoint]
     */
    @Before(EXECUTION)
    public void doBeforeInServiceLayer(JoinPoint joinPoint) {
        startTimeMillis = System.currentTimeMillis();//记录方法开始执行的时间
    }

    /**
     * @return void
     * @MethodName doAfterInServiceLayer
     * @Description 方法调用后触发，记录结束时间
     * @Author chupengtang
     * @CreateDate 2018/9/19 12:09
     * @Param [joinPoint]
     */
    @After(EXECUTION)
    public void doAfterInServiceLayer(JoinPoint joinPoint) {
        endTimeMillis = System.currentTimeMillis();
        this.printOptLog();
    }

    /**
     * @return void
     * @MethodName AfterReturnning
     * @Description 后置通知
     * @Author chupengtang
     * @CreateDate 2018/9/19 12:22
     * @Param [returnVal]
     */
    @AfterReturning(value = EXECUTION, returning = "returnVal")
    public void AfterReturnning(Object returnVal) {

    }

    /**
     * @return
     * @MethodName
     * @Description 环绕通知
     * @Author chupengtang
     * @CreateDate 2018/9/19 12:24
     * @Param
     */
    @Around(EXECUTION)
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //获取Request信息
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        requestURI = request.getRequestURI();//请求地址
        inputParamMap = request.getParameterMap();//入参
        outputParamMap = new HashMap<>();//出参
        Object result = proceedingJoinPoint.proceed();// result的值就是被拦截方法的返回值
        outputParamMap.put("result", result);
        return result;
    }

    /**
     * @return
     * @MethodName
     * @Description 异常抛出通知
     * @Author chupengtang
     * @CreateDate 2018/9/19 12:28
     * @Param
     */
    @AfterThrowing(value = EXECUTION, throwing = "e")
    public void afterThrowable(JoinPoint joinPoint, Throwable e) {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = sra.getResponse();
        response.setContentType("application/json;charset=UTF-8");
        try {
            ApiResponse apiResponse = null;
            if (e instanceof BussinessException) {
                //自定义异常
                BussinessException bussinessException = (BussinessException) e;
                apiResponse = new ApiResponse(ApiResponse.FAIL_CODE, bussinessException.getMessage());
            } else {
                apiResponse = new ApiResponse(ApiResponse.FAIL_CODE, "system error!");
            }
            String param = gson.toJson(apiResponse);
            ServletOutputStream sos = response.getOutputStream();
            sos.write(param.getBytes("UTF-8"));
            sos.flush();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        e.printStackTrace();
    }

    /**
     * 日志输出
     */
    private void printOptLog() {
        String optTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTimeMillis);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n=====================================Request start===============================================\n");
        stringBuilder.append("\n请求接口:" + requestURI);
        stringBuilder.append("\n开始时间:" + optTime);
        stringBuilder.append("\n请求接口:" + (endTimeMillis - startTimeMillis) + "ms");
        stringBuilder.append("\n请求参数:" + new Gson().toJson(inputParamMap));
        stringBuilder.append("\n响应参数:\n" + outputParamMap.get("result"));
        stringBuilder.append("\n\n=====================================Request  end===============================================\n");
        logger.debug(stringBuilder.toString());
    }
}
