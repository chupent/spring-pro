package com.wx.cp.comm.net;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Enumeration;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName RequestWrapper
 * @Description TODO
 * HttpServletRequestWrapper把request保存下来,然后通过过滤器把保存下来的request再填充进去
 * @createdate 2019/5/13 星期一 11:42
 */
public class RequestWrapper extends HttpServletRequestWrapper{

    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    private RequestBody requestBody;

    public RequestWrapper(HttpServletRequest request) {
        super(request);
        String url    = request.getContextPath() + request.getServletPath();
        String contentType = request.getContentType();
        LOGGER.info("访问类型:"+ contentType);

        String params = "无";
        if (null!=contentType&&(contentType.contains("text")||contentType.contains("json")||contentType.contains("javascript"))){
            params = this.getBodyString(request);
        }else { //multipart/form-data  、 application/x-www-form-urlencoded
            params = getBodyStringFromParameter(request);
        }

        this.requestBody = new RequestBody(url,params);
        LOGGER.info("访问参数:"+ this.requestBody);
    }
    public RequestWrapper(HttpServletRequest request,RequestBody requestBody) {
        super(request);
        this.requestBody = requestBody;
        LOGGER.info("访问参数:"+ this.requestBody);
    }



    @Override
    public ServletInputStream getInputStream() throws IOException {
        return new ServletInputStream() {
            @Override
            public boolean isFinished() { return false; }
            @Override
            public boolean isReady() { return false; }
            @Override
            public void setReadListener(ReadListener readListener) {}
            @Override
            public int read() throws IOException {
                return new ByteArrayInputStream(requestBody.getParamstoByteArray()).read();
            }
        };
    }

    /**
     * 保存参数列表
     */
    public void saveRequestBody(){
        HttpServletRequest request = (HttpServletRequest)this.getRequest();
        request.getSession().setAttribute("requestBody",this.requestBody);
    }
    public String getBodyString(ServletRequest request){
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = null;
        InputStream inputStream = null;
        try {
            inputStream = request.getInputStream();
            if (null!=inputStream){
                reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
            }else {
                sb.append("");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (null!=reader) reader.close();
                if (null!=inputStream) inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
    public String getBodyStringFromParameter(ServletRequest request){
        StringBuilder sb = new StringBuilder("");
        Enumeration enu=request.getParameterNames();
        while(enu.hasMoreElements()){
            String paraName  = (String)enu.nextElement();
            String paraValue = request.getParameter(paraName);
            sb.append(paraName).append("=").append(paraValue).append("&");
        }
        int n = sb.lastIndexOf("&");
        return n > 0 ? sb.substring(0,n):sb.toString();
    }
}
