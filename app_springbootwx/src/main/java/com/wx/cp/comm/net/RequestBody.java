package com.wx.cp.comm.net;
import java.io.Serializable;
import java.nio.charset.Charset;


/**
 * @author chupengtang
 * @version 1.0
 * @ClassName RequestBody
 * @Description TODO 记录Request信息
 * @createdate 2019/5/13 星期一 11:38
 */
public class RequestBody implements Serializable {
    private String url;//地址
    private String params; //参数

    public RequestBody(String url) {
        this.url = url;
    }

    public RequestBody(String url, String params) {
        this.url = url;
        this.params = params;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getParams() {
        return params;
    }
    public void setParams(String params) {
        this.params = params;
    }
    public byte[] getParamstoByteArray() {
        return params.getBytes(Charset.forName("UTF-8"));
    }

    @Override
    public String toString() {
        return "RequestBody{" + "url='" + url + '\'' + ", params='" + params + '\'' + '}';
    }
}
