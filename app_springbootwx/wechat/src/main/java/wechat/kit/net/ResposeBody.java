package wechat.kit.net;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName Resut
 * @Description TODO 微信请求响应体接收容器
 * @createdate 2019/5/22 星期三 15:50
 */
public class ResposeBody<T> {
    private Integer errcode;
    private String errmsg;
    private T t;

    public ResposeBody(T t) {
        this.t = t;
    }

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
