package com.cp.shanghai.util.resolver;

import com.alibaba.druid.support.json.JSONUtils;
import com.cp.shanghai.util.exception.BussinessException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName ExceptionResolver
 * @Description TODO 异常分解器
 * @createdate 2018/9/27 星期四 10:40
 */
public class ExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        //判断是否ajax请求
        if (!(httpServletRequest.getHeader("accept").indexOf("application/json") > 1
                || (httpServletRequest.getHeader("X-Requested-With") != null
                && httpServletRequest.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
            // 如果不是ajax，JSP格式返回
            // 除了业务请求，其他请求统一归为系统异常
            Map<String, Object> map = new HashMap<>();
            map.put("success", "false");
            //判断是否业务异常
            if (e instanceof BussinessException) {
                map.put("errorMsg", e.getMessage());
            } else {
                map.put("errorMsg", "系统异常");
            }
            return new ModelAndView("redirect:/error.jsp", map);
        } else {
            try {
                httpServletResponse.setContentType("application/json;charset=UTF-8");
                PrintWriter writer = httpServletResponse.getWriter();
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("success", false);
                //判断是否业务异常
                if (e instanceof BussinessException) {
                    map.put("errorMsg", e.getMessage());
                } else {
                    map.put("errorMsg", "系统异常");
                }
                writer.write(JSONUtils.toJSONString(map));
                writer.flush();
                writer.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }
}
