package com.cp.app.core.comm.security;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName ResultDispose
 * @Description TODO
 * @createdate 2019/4/2 星期二 14:26
 */
public class ResultDispose {
    /**
     * @param os
     * @param t
     * @param <T>
     */
    public static<T> void toJsonResult(OutputStream os,T t){
        OutputStreamWriter osw  = null;
        try {
            osw =new OutputStreamWriter(os,"UTF-8");
            new MappingJackson2HttpMessageConverter().getObjectMapper().writeValue(osw,t);
            osw.flush();
            os.flush();
            os.close();
            osw.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
