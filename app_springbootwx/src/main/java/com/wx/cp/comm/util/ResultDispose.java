package com.wx.cp.comm.util;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName ResultDispose
 * @Description TODO 响应结果处理类
 * @createdate 2019/4/2 星期二 14:26
 */
public class ResultDispose {
    /**
     * 将流转成json格式输出
     * @param os
     * @param t
     * @param <T>
     */
    public static<T> void toJsonResult(OutputStream os,T t){
        ObjectMapper om = new MappingJackson2HttpMessageConverter().getObjectMapper();
        OutputStreamWriter osw  = null;
        try {
            osw =new OutputStreamWriter(os,"UTF-8");
            om.writeValue(osw,t);
            os.flush();
            os.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
